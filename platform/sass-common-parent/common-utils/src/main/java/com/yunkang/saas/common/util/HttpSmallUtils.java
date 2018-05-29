package com.yunkang.saas.common.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HttpSmallUtils {

	public static <T> T doGet(HttpRequest request, final Class<T> responseType) {
		Function<byte[], T> convertResponse = new Function<byte[], T>() {
			@Override
			@SuppressWarnings("unchecked")
			public T apply(byte[] input) {
				if (input == null || input.length <= 0) {
					return null;
				} else if (byte[].class.equals(responseType)) {
					return (T) input;
				} else {
					return JsonUtils.toObject(new String(input, StandardCharsets.UTF_8), responseType);
				}
			}
		};
		return executeRequest(request, RequestMethod.GET, convertResponse);
	}

	public static <T> T doPost(HttpRequest request, final Class<T> responseType) {
		Function<byte[], T> convertResponse = new Function<byte[], T>() {
			@Override
			public T apply(byte[] input) {
				return JsonUtils.toObject(new String(input, StandardCharsets.UTF_8), responseType);
			}
		};
		return executeRequest(request, RequestMethod.POST, convertResponse);
	}

	public static <T> T doPut(HttpRequest request, final Class<T> responseType) {
		Function<byte[], T> convertResponse = new Function<byte[], T>() {
			@Override
			public T apply(byte[] input) {
				return JsonUtils.toObject(new String(input, StandardCharsets.UTF_8), responseType);
			}
		};
		return executeRequest(request, RequestMethod.PUT, convertResponse);
	}

	public static <T> T doPatch(HttpRequest request, final Class<T> responseType) {
		Function<byte[], T> convertResponse = new Function<byte[], T>() {
			@Override
			public T apply(byte[] input) {
				return JsonUtils.toObject(new String(input, StandardCharsets.UTF_8), responseType);
			}
		};
		return executeRequest(request, RequestMethod.PATCH, convertResponse);
	}

	public static <T> T doDelete(HttpRequest request, final Class<T> responseType) {
		Function<byte[], T> convertResponse = new Function<byte[], T>() {
			@Override
			public T apply(byte[] input) {
				return JsonUtils.toObject(new String(input, StandardCharsets.UTF_8), responseType);
			}
		};
		return executeRequest(request, RequestMethod.DELETE, convertResponse);
	}

	private static <T> T executeRequest(HttpRequest request, RequestMethod method,
			Function<byte[], T> serializeFunction) {
		OutputStream output = null;
		int statusCode = 0;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(request.getUrl()).openConnection();
			conn.setRequestMethod(method.name());
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Accept", "*/*");
			request.getHeads().forEach((k, v) -> {
				conn.setRequestProperty(k, v);
			});
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			if (request.getBody() != null) {
				sendByteArray(conn.getOutputStream(), request.getBody());
			}

			conn.connect();

			statusCode = conn.getResponseCode();

			if (statusCode == HttpURLConnection.HTTP_OK) {
				return serializeFunction.apply(extractByteArray(conn.getInputStream()));
			}
			throw new RuntimeException(String.format("HttpRequest operation failed([%s]) for Url:%s",
					new Object[] { statusCode, request.getUrl() }));
		} catch (Throwable ex) {
			throw new RuntimeException(String.format("HttpRequest operation failed([%s] - %s) for %s",
					new Object[] { statusCode, request.getUrl(), ex }));
		} finally {
			close(output);
		}
	}

	private static byte[] extractByteArray(InputStream from) throws IOException {
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = from.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			return out.toByteArray();
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			out.close();
			close(from);
		}
	}

	private static void sendByteArray(OutputStream output, Object body) {
		try {
			if (body instanceof byte[]) {
				output.write((byte[]) body);
			} else {
				output.write(JsonUtils.toJson(body).getBytes(StandardCharsets.UTF_8));
			}
			output.flush();
		} catch (IOException e) {
			// ignore
		} finally {
			close(output);
		}
	}

	private static void close(Closeable sr) {
		if (sr != null) {
			try {
				sr.close();
			} catch (IOException ex) {
				// ignore
			}
		}
	}

	public enum RequestMethod {
		POST, GET, PUT, PATCH, DELETE
	}

	public static class HttpRequest {
		private static final String BOUNDARY = "----HV2ymHFg03ehbqgZCaKO6jyH";
		private static final String START_LINE = "------HV2ymHFg03ehbqgZCaKO6jyH\r\n";
		private static final String END_LINE = "\r\n\r\n------HV2ymHFg03ehbqgZCaKO6jyH--\r\n";

		private String url;
		private Map<String, String> heads = new HashMap<String, String>();
		private Object body;

		public HttpRequest(String url) {
			this.url = url;
			heads.put("Content-Type", "application/json; charset=UTF-8");
		}

		/**
		 * 普通请求
		 * 
		 * @param url
		 * @param body
		 */
		public HttpRequest(String url, Object body) {
			this.url = url;
			this.body = body;
			heads.put("Content-Type", "application/json; charset=UTF-8");
		}

		/**
		 * 文件上传请求
		 * 
		 * @param url
		 *            请求地址
		 * @param fileName
		 *            文件名称
		 * @param fileData
		 *            文件二进制
		 */
		public HttpRequest(String url, String fileName, byte[] fileData) {
			this.url = url;
			byte[] disposition = String
					.format("Content-Disposition:form-data; name=\"file\"; filename=\"%s\"\r\n", fileName).getBytes();
			byte[] contentType = "Content-Type:application/octet-stream\r\n\r\n".getBytes();
			heads.put("Connection", "Keep-Alive");
			heads.put("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
			ByteArrayOutputStream byteBody = null;
			try {
				byteBody = new ByteArrayOutputStream();
				byteBody.write(START_LINE.getBytes());
				byteBody.write(disposition);
				byteBody.write(contentType);
				byteBody.write(fileData);
				byteBody.write(END_LINE.getBytes());
				byteBody.flush();
				setBody(byteBody.toByteArray());
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			} finally {
				try {
					byteBody.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		public void setRequestProperty(String k, String v) {
			heads.put(k, v);
		}

		public String getUrl() {
			return url;
		}

		public void setBody(Object body) {
			this.body = body;
		}

		public Object getBody() {
			return body;
		}

		public Map<String, String> getHeads() {
			return heads;
		}

	}

}
