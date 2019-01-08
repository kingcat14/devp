package net.aicoder.speedcloud.apapter.yunkang.client;




import com.devin.ciserver.model.job.response.BuildDetail;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineBuildLogVO;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ShConsoleOutputVO;
import net.aicoder.speedcloud.business.pipeline.exec.vo.StageConsoleOutputVO;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by devin on 2018/11/19.
 */
@Slf4j
public class ConvertUtil {

    public static PipelineBuildLogVO buildWithDetailToBuildDetail(BuildDetail bwd, String pipelineNameEn){
        if(bwd==null){
            return null;
        }

        String consoleOutputText="";
        String consoleOutputTmp="";
        try {
            if(StringUtils.isNotEmpty(bwd.getConsoleOutputText())){
                consoleOutputText=bwd.getConsoleOutputText();
                int i=consoleOutputText.indexOf(pipelineNameEn);
                consoleOutputText=consoleOutputText.substring(i);
                consoleOutputText=consoleOutputText.replaceFirst(pipelineNameEn+"\r\n","");
                consoleOutputTmp=consoleOutputText;
                //consoleOutputText=consoleOutputText.replaceAll("\r\n","</br>");
            }
        }catch (Exception e){
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        PipelineBuildLogVO bd=new PipelineBuildLogVO(
                bwd.getDisplayName(),
                bwd.getFullDisplayName(),
                bwd.getId(),
                bwd.getResult(),
                consoleOutputText,
                consoleOutputConvert(consoleOutputTmp, bwd.getResult()),
                bwd.getNumber(),
                bwd.getDuration(),
                bwd.getTimestamp(),
                sdf.format(new Date(bwd.getTimestamp())));
        return bd;
    }

    public static List<StageConsoleOutputVO> consoleOutputConvert(String consoleOutputText, String flag){
        try {
            //System.out.println("运行结果:"+flag);
            List<StageConsoleOutputVO> stageConsoleOutputs=new ArrayList<StageConsoleOutputVO>();
            BufferedReader br=new BufferedReader((new StringReader(consoleOutputText)));
            String line;
            StageConsoleOutputVO stageConsoleOutput=null;
            String stageName;
            boolean shFlag=false;
            List<ShConsoleOutputVO> shConsoleOutputs=null;
            while ( (line = br.readLine()) != null ) {
                //System.out.println(line);
                if(line.startsWith("Started by")){
                    continue;
                }
                if(line.startsWith("[Pipeline] node")){
                    continue;
                }
                if(line.startsWith("Running on")){
                    continue;
                }
                if(line.equals("[Pipeline] {")){
                    continue;
                }
                if(line.equals("[Pipeline] }")){
                    continue;
                }
                if(line.equals("[Pipeline] // timestamps")){
                    continue;
                }
                if(line.equals("[Pipeline] // stage")){
                    shFlag=false;
                    continue;
                }

                if("[Pipeline] stage".equals(line)){
                    stageConsoleOutput=new StageConsoleOutputVO();
                    stageConsoleOutputs.add(stageConsoleOutput);
                    String stageConsoleOutputText=line;
                    stageConsoleOutput.setConsoleOutputText(stageConsoleOutputText+"</br>");
                    continue;
                }
                if(line.startsWith("[Pipeline] { (")){
                    stageName=line.substring(line.indexOf("(")+1,line.indexOf(")"));
                    stageConsoleOutput.setStageName(stageName);

                    String stageConsoleOutputText=stageConsoleOutput.getConsoleOutputText();//stageConsoleOutputText追加
                    stageConsoleOutput.setConsoleOutputText(stageConsoleOutputText+line+"</br>");//stageConsoleOutputText追加

                    shConsoleOutputs=new ArrayList<ShConsoleOutputVO>();
                    stageConsoleOutput.setShConsoleOutputs(shConsoleOutputs);
                    continue;
                }
                if("[Pipeline] sh".equals(line)){
                    stageConsoleOutput.getShConsoleOutputs().add(new ShConsoleOutputVO());
                    String stageConsoleOutputText=stageConsoleOutput.getConsoleOutputText();//stageConsoleOutputText追加
                    stageConsoleOutput.setConsoleOutputText(stageConsoleOutputText+line+"</br>");//stageConsoleOutputText追加
                    shFlag=true;
                    continue;
                }
                if(shFlag){
                    ShConsoleOutputVO shConsoleOutput=stageConsoleOutput.getShConsoleOutputs().get(stageConsoleOutput.getShConsoleOutputs().size()-1);
                    String oldString=shConsoleOutput.getConsoleOutputText();
                    String newString="";
                    if(!oldString.equals("")){
                        newString=oldString+"\r\n"+line;
                    }else{
                        newString=line;
                    }
                    shConsoleOutput.setConsoleOutputText(newString);
                    String stageConsoleOutputText=stageConsoleOutput.getConsoleOutputText();//stageConsoleOutputText追加
                    stageConsoleOutput.setConsoleOutputText(stageConsoleOutputText+line+"</br>");//stageConsoleOutputText追加
                    continue;
                }
            }
            if(stageConsoleOutputs.size()>0){
                for(int i=stageConsoleOutputs.size()-1;i>=0;i--){
                    if(i==stageConsoleOutputs.size()-1){
                        stageConsoleOutputs.get(i).setResult(flag);
                    }else{
                        if(stageConsoleOutputs.get(i+1).getShConsoleOutputs().size()==0){
                            stageConsoleOutputs.get(i).setResult(Constants.RESPONSE_FLAG_FAILURE);
                        }else{
                            stageConsoleOutputs.get(i).setResult(Constants.RESPONSE_FLAG_SUCCESS);
                        }
                    }
                    if(Constants.RESPONSE_FLAG_FAILURE.equals(stageConsoleOutputs.get(i).getResult())){
                        List<ShConsoleOutputVO> shConsoleOutputs1=stageConsoleOutputs.get(i).getShConsoleOutputs();
                        for(int j=0;j<shConsoleOutputs1.size();j++){
                            if(j==(shConsoleOutputs1.size()-1)){
                                shConsoleOutputs1.get(j).setResult(Constants.RESPONSE_FLAG_FAILURE);
                            }else{
                                shConsoleOutputs1.get(j).setResult(Constants.RESPONSE_FLAG_SUCCESS);
                            }
                        }
                    }
                }
            }
            return stageConsoleOutputs;
        }catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<StageConsoleOutputVO>();
        }
    }
}