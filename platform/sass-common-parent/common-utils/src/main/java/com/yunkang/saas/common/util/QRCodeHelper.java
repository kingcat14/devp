package com.yunkang.saas.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 条形码/二维码生成/解析工具
 * 
 * @auditor XinmingYan @time 2017年6月28日 上午10:25:53 @versions 0.0.1
 * @packagePath com.yunkanghealth.yktechcom.tools.QRCodeHelper.java
 */
public class QRCodeHelper {

	private static String FORMATNAME = "png";

	private static String CHARACTER = "UTF-8";

	/**
	 * 条形码生成(CODE_39)
	 * @param text 内容
	 * @return
	 */
	public static byte[] encoderBarCode(String text) {
		return encoderCode(text, 140,30, BarcodeFormat.CODE_39,FORMATNAME, CHARACTER);
	}
	
	/**
	 * 条形码生成(CODE_39)
	 * @param text 内容
	 * @param width 宽度(像素)
	 * @param height 高度(像素)
	 * @return
	 */
	public static byte[] encoderBarCode(String text,int width, int height ) {
		return encoderCode(text, width, height, BarcodeFormat.CODE_39,FORMATNAME, CHARACTER);
	}
	
	/**
	 * 二维码生成
	 * @param text 内容
	 * @return
	 */
	public static byte[] encoderQRCode(String text) {
		return encoderCode(text, 300, 300, BarcodeFormat.QR_CODE,FORMATNAME, CHARACTER);
	}
	
	/**
	 * 二维码生成
	 * @param text 内容
	 * @param width 宽度(像素)
	 * @param height 高度(像素)
	 * @return
	 */
	public static byte[] encoderQRCode(String text,int width, int height ) {
		return encoderCode(text, width, height, BarcodeFormat.QR_CODE,FORMATNAME, CHARACTER);
	}
	

	/**
	 * 解析图片内容
	 * @param image
	 * @return
	 */
	public static String parseCode(byte[] image) {
		return parseCode(image, CHARACTER);
	}

	/**
	 * 生成图片流
	 * 
	 * @param text 内容
	 * @param width 图片宽度
	 * @param height 图片高度
	 * @param formatName 后缀名, 如： png
	 * @param character 字符编号, 如 ：UTF-8
	 * @return 图片流
	 */
	private static byte[] encoderCode(String text, int width, int height,BarcodeFormat format, String formatName, String character) {
		ByteArrayOutputStream bo = null;
		try {
			HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, character);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, format, width, height,
					hints);
			bo = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, formatName, bo);
			return bo.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(bo);
		}
		return null;
	}

	/**
	 * 解析图片内容
	 * 
	 * @param Image 图片流
	 * @param 内容的字符集编码, 如 UTF-8
	 * @return
	 */
	private static String parseCode(byte[] QRImage, String character) {
		ByteArrayInputStream io = null;
		try {
			io = new ByteArrayInputStream(QRImage);
			LuminanceSource source = new BufferedImageLuminanceSource(ImageIO.read(io));
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(io);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		File file = new File("e://test.png");
		ByteArrayInputStream bais = new ByteArrayInputStream(QRCodeHelper.encoderBarCode("81237612",140,30));
		ImageIO.write(ImageIO.read(bais), "png", file);
		IOUtils.closeQuietly(bais);
		// ///////////////////////////////////
		byte[] buffer = new byte[1024];
		FileInputStream input = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while (input.read(buffer) != -1) {
			out.write(buffer);
		}
		out.flush();
		System.out.println(QRCodeHelper.parseCode(out.toByteArray()));
		//////////////////////////////////////
		IOUtils.closeQuietly(input);
		IOUtils.closeQuietly(out);
	}
}
