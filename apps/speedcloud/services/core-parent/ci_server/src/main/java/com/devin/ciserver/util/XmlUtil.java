package com.devin.ciserver.util;

import com.devin.ciserver.model.job.BaseJob;
import com.devin.ciserver.model.job.PipelineJob;
import com.devin.ciserver.model.job.PipelineStage;
import com.devin.ciserver.model.job.StringParm;
import com.devin.ciserver.model.xml.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by devin on 2018/8/8.
 */
public class XmlUtil {
    /**
     *
     * @param obj
     * @param load
     * @return
     * @throws JAXBException
     */
    public static String beanToXml(Object obj,Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }
    public static String parseBaseJobToXml(BaseJob baseJob){
        Job job=new Job();
        try {
            if(baseJob!=null){
                String desc=baseJob.getDesc();
                if(desc!=null){
                    job.setDescription(desc);
                }
                List<StringParm> stringParms=baseJob.getStringParms();
                List<String> shellCmds=baseJob.getShellCmd();
                if(stringParms!=null&&stringParms.size()>0){
                    List<StringParameterDefinition> spds=new ArrayList<StringParameterDefinition>();
                    for(StringParm sp:stringParms){
                        StringParameterDefinition spd=new StringParameterDefinition();
                        spd.setName(sp.getName());
                        spd.setDefaultValue(sp.getDefaultValue());
                        spd.setDescription(sp.getDescription());
                        spds.add(spd);
                    }
                    HudsonModelParametersDefinitionProperty hmpd=new HudsonModelParametersDefinitionProperty();
                    ParameterDefinitions pds=new ParameterDefinitions();
                    hmpd.setParameterDefinitions(pds);
                    pds.setSpds(spds);
                    job.getProperties().setHmpd(hmpd);
                }
                if(shellCmds!=null&&shellCmds.size()>0){
                    List<HudsonTasksShell> hudsonTasksShells =new ArrayList<HudsonTasksShell>();
                    for(String cmd:shellCmds){
                        HudsonTasksShell hudsonTasksShell=new HudsonTasksShell();
                        hudsonTasksShell.setCommand(cmd);
                        hudsonTasksShells.add(hudsonTasksShell);
                    }
                    job.getBuilders().setHudsonTasksShells(hudsonTasksShells);
                }
            }
            String xml=beanToXml(job,Job.class);
            return xml;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String baseJobToXml(BaseJob baseJob) throws Exception {
        String xml;
        try {
            InputStream jobIs = XmlUtil.class.getResourceAsStream("/jobTemplate/job.xml");
            BufferedReader in = new BufferedReader(new InputStreamReader(jobIs));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null){
                buffer.append(line);
            }
            xml=buffer.toString();
            List<String> params=new ArrayList<String>();
            params.add(baseJob.getDesc());
            params.add(Long.toString(baseJob.getKeepBuildDay()));
            params.add(Long.toString(baseJob.getKeepBuildNumber()));
            List<StringParm> stringParms=baseJob.getStringParms();
            StringBuffer sb=new StringBuffer("");
            if(stringParms!=null&&stringParms.size()>0){
                sb.append("<hudson.model.ParametersDefinitionProperty>");
                sb.append("<parameterDefinitions>");
                for(StringParm sp:stringParms){
                    sb.append("<hudson.model.StringParameterDefinition>");
                    sb.append("<name>"+sp.getName()+"</name>");
                    sb.append("<description>"+sp.getDescription()+"</description>");
                    sb.append("<defaultValue>"+sp.getDefaultValue()+"</defaultValue>");
                    sb.append("</hudson.model.StringParameterDefinition>");
                }
                sb.append("</parameterDefinitions>");
                sb.append("</hudson.model.ParametersDefinitionProperty>");
            }

            params.add(sb.toString());
            StringBuffer sb1=new StringBuffer("");
            List<String> shells=baseJob.getShellCmd();
            if(shells!=null&&shells.size()>0){
                for(String shell:shells){
                    sb1.append("<hudson.tasks.Shell>");
                    sb1.append("<command>"+shell+"</command>");
                    sb1.append("</hudson.tasks.Shell>");
                }
            }
            params.add(sb1.toString());
            xml=MessageFormat.format(xml,params.toArray());
        }catch (Exception ex){
            throw ex;
        }
        return xml;
    }

    public static String pipelineJobToXml(PipelineJob pipelineJob) throws Exception {
        String xml;
        try {
            InputStream jobIs = XmlUtil.class.getResourceAsStream("/jobTemplate/pipelineJob.xml");
            BufferedReader in = new BufferedReader(new InputStreamReader(jobIs));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null){
                buffer.append(line);
            }
            xml=buffer.toString();
            List<String> params=new ArrayList<String>();
            params.add(pipelineJob.getDesc());
            params.add(Long.toString(pipelineJob.getKeepBuildDay()));
            params.add(Long.toString(pipelineJob.getKeepBuildNumber()));
            List<StringParm> stringParms=pipelineJob.getStringParms();
            StringBuffer sb=new StringBuffer("");
            if(stringParms!=null&&stringParms.size()>0){
                sb.append("<hudson.model.ParametersDefinitionProperty>");
                sb.append("<parameterDefinitions>");
                for(StringParm sp:stringParms){
                    sb.append("<hudson.model.StringParameterDefinition>");
                    sb.append("<name>"+sp.getName()+"</name>");
                    sb.append("<description>"+sp.getDescription()+"</description>");
                    sb.append("<defaultValue>"+sp.getDefaultValue()+"</defaultValue>");
                    sb.append("</hudson.model.StringParameterDefinition>");
                }
                sb.append("</parameterDefinitions>");
                sb.append("</hudson.model.ParametersDefinitionProperty>");
            }

            params.add(sb.toString());
            StringBuffer sb1=new StringBuffer("");
            List<PipelineStage> pipelineStages=pipelineJob.getPipelineStages();
            sb1.append("\npipeline {\n");
            sb1.append(" agent any \n");
            sb1.append(" stages {\n");
            Map<String,String> mapStage=new HashMap<String, String>();
            if(pipelineStages==null||pipelineStages.size()==0){
                throw new Exception("至少需要1个stage!");
            }
            for(PipelineStage ps:pipelineStages){
                if(mapStage.containsKey(ps.getStageName())){
                    throw new Exception("Duplicate stage name: ["+ps.getStageName()+"]");
                }
                mapStage.put(ps.getStageName(),ps.getStageName());
                sb1.append("  stage(&apos;"+ps.getStageName()+"&apos;) {\n");
                sb1.append("   steps {\n");
                if(ps.getShellCmd()==null||ps.getShellCmd().size()==0){
                    throw new Exception("stage["+ps.getStageName()+"]至少需要1个shellCmd!");
                }
                for(String sh:ps.getShellCmd()){
                    sb1.append("    sh &apos;&apos;&apos;\n");
                    sb1.append("     "+sh+"\n");
                    sb1.append("    &apos;&apos;&apos;\n");
                }
                sb1.append("   }\n");
                sb1.append("  }\n");
            }
            sb1.append(" }\n");
            sb1.append("}");
            params.add(sb1.toString());
            xml=MessageFormat.format(xml,params.toArray());
        }catch (Exception ex){
            throw ex;
        }
        return xml;
    }

    public static void main(String[] args){

    }
}
