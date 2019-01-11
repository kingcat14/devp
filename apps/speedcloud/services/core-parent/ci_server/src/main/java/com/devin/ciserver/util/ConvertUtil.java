package com.devin.ciserver.util;

import com.devin.ciserver.model.Constants;
import com.devin.ciserver.model.job.BuildDetail;
import com.devin.ciserver.model.job.ShConsoleOutput;
import com.devin.ciserver.model.job.StageConsoleOutput;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by devin on 2018/8/9.
 */
public class ConvertUtil {
    public static BuildDetail buildWithDetailToBuildDetail(BuildWithDetails bwd) throws Exception {
        if (bwd == null) {
            return null;
        }
        String consoleOutputText = "";
        String consoleOutputHtml = "";
        try {
            if (null != bwd.getConsoleOutputText() && !bwd.getConsoleOutputText().equals("")) {
                consoleOutputText = bwd.getConsoleOutputText();
            }
        } catch (Exception e) {
        }

        BuildDetail bd = null;
        try {
            if (null != bwd.getConsoleOutputHtml() && !bwd.getConsoleOutputHtml().equals("")) {
                consoleOutputHtml = bwd.getConsoleOutputHtml();
            }
            bd = new BuildDetail(
                    bwd.getDisplayName(),
                    bwd.getFullDisplayName(),
                    bwd.getId(),
                    null,
                    consoleOutputText,
                    consoleOutputHtml,
//                consoleOutputConvert(consoleOutputText,bwd.getResult().name()),
                    bwd.getNumber(),
                    bwd.getDuration(),
                    bwd.getTimestamp());

            if (bwd.getResult() != null) {
                List<StageConsoleOutput> list = consoleOutputConvert(consoleOutputText, bwd.getResult().name());
                bd.setPipelineConsoleOutputText(list);
                bd.setResult(bwd.getResult().name());
            }
        } catch (Exception e) {
        }

        if (bd == null) {
            bd = new BuildDetail(
                    bwd.getDisplayName(),
                    bwd.getFullDisplayName(),
                    bwd.getId(),
                    bwd.getResult().toString(),
                    consoleOutputText,
                    consoleOutputHtml,
//                consoleOutputConvert(consoleOutputText,bwd.getResult().name()),
                    bwd.getNumber(),
                    bwd.getDuration(),
                    bwd.getTimestamp());
        }
        return bd;
    }

    public static List<BuildDetail> buildWithDetailsToBuildDetails(List<BuildWithDetails> bwds) throws Exception {
        if (bwds == null) {
            return null;
        }
        List<BuildDetail> bds = new ArrayList<BuildDetail>();
        for (BuildWithDetails bwd : bwds) {
            bds.add(buildWithDetailToBuildDetail(bwd));
        }
        return bds;
    }

    public static BuildDetail buildToBuildDetail(Build build) throws Exception {
        if (build == null) {
            return null;
        }
        BuildWithDetails bwd = build.details();
        if (bwd == null) {
            return null;
        }
        return buildWithDetailToBuildDetail(bwd);
    }

    public static List<BuildDetail> buildsToBuildDetails(List<Build> builds) throws Exception {
        if (builds == null) {
            return null;
        }
        List<BuildDetail> buildDetails = new ArrayList<BuildDetail>();
        for (Build build : builds) {
            buildDetails.add(buildToBuildDetail(build));
        }
        return buildDetails;
    }

    public static List<StageConsoleOutput> consoleOutputConvert(String consoleOutputText, String flag) {
        try {
            System.out.println("运行结果:" + flag);
            List<StageConsoleOutput> stageConsoleOutputs = new ArrayList<>();
            BufferedReader br = new BufferedReader((new StringReader(consoleOutputText)));
            String line;
            StageConsoleOutput stageConsoleOutput = null;
            String stageName;
            boolean shFlag = false;
            List<ShConsoleOutput> shConsoleOutputs = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("Started by")) {
                    continue;
                }
                if (line.startsWith("[Pipeline] node")) {
                    continue;
                }
                if (line.startsWith("Running on")) {
                    continue;
                }
                if (line.equals("[Pipeline] {")) {
                    continue;
                }
                if (line.equals("[Pipeline] }")) {
                    continue;
                }
                if (line.equals("[Pipeline] // timestamps")) {
                    continue;
                }
                if (line.equals("[Pipeline] // stage")) {
                    shFlag = false;
                    continue;
                }

                if ("[Pipeline] stage".equals(line)) {
                    stageConsoleOutput = new StageConsoleOutput();
                    stageConsoleOutputs.add(stageConsoleOutput);
                    continue;
                }
                if (line.startsWith("[Pipeline] { (")) {
                    stageName = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    stageConsoleOutput.setStageName(stageName);
                    shConsoleOutputs = new ArrayList<ShConsoleOutput>();
                    stageConsoleOutput.setShConsoleOutputs(shConsoleOutputs);
                    continue;
                }
                if ("[Pipeline] sh".equals(line)) {
                    stageConsoleOutput.getShConsoleOutputs().add(new ShConsoleOutput());
                    shFlag = true;
                    continue;
                }
                if (shFlag) {
                    ShConsoleOutput shConsoleOutput = stageConsoleOutput.getShConsoleOutputs().get(stageConsoleOutput.getShConsoleOutputs().size() - 1);
                    String oldString = shConsoleOutput.getConsoleOutputText();
                    String newString = "";
                    if (!oldString.equals("")) {
                        newString = oldString + "\r\n" + line;
                    } else {
                        newString = line;
                    }
                    shConsoleOutput.setConsoleOutputText(newString);
                    continue;
                }
            }
            if (stageConsoleOutputs.size() > 0) {
                for (int i = stageConsoleOutputs.size() - 1; i >= 0; i--) {
                    if (i == stageConsoleOutputs.size() - 1) {
                        stageConsoleOutputs.get(i).setResult(flag);
                    } else {
                        if (stageConsoleOutputs.get(i + 1).getShConsoleOutputs().size() == 0) {
                            stageConsoleOutputs.get(i).setResult(Constants.RESPONSE_FLAG_FAILURE);
                        } else {
                            stageConsoleOutputs.get(i).setResult(Constants.RESPONSE_FLAG_SUCCESS);
                        }
                    }
                    if (Constants.RESPONSE_FLAG_FAILURE.equals(stageConsoleOutputs.get(i).getResult())) {
                        List<ShConsoleOutput> shConsoleOutputs1 = stageConsoleOutputs.get(i).getShConsoleOutputs();
                        for (int j = 0; j < shConsoleOutputs1.size(); j++) {
                            if (j == (shConsoleOutputs1.size() - 1)) {
                                shConsoleOutputs1.get(j).setResult(Constants.RESPONSE_FLAG_FAILURE);
                            } else {
                                shConsoleOutputs1.get(j).setResult(Constants.RESPONSE_FLAG_SUCCESS);
                            }
                        }
                    }
                }
            }
            return stageConsoleOutputs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<StageConsoleOutput>();
        }
    }

}
