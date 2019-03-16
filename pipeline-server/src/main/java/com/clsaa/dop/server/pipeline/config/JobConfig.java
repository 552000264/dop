package com.clsaa.dop.server.pipeline.config;
/**
 * 生成创建jenkins是多需要的xml
 *
 * @author 张富利
 * @since 2019-03-09
 */

public class JobConfig {
    private String version;
    private String script;
    private String xml;

    public JobConfig(){
        this.version = "1.1";
        this.script = "";
        this.xml = "<?xml version=\'"+this.version+"\' encoding='UTF-8'?>\n" +
                "<flow-definition plugin=\"workflow-job@2.31\">\n" +
                "  <actions>\n" +
                "    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobAction plugin=\"pipeline-model-definition@1.3.4.1\"/>\n" +
                "    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction plugin=\"pipeline-model-definition@1.3.4.1\">\n" +
                "      <jobProperties/>\n" +
                "      <triggers/>\n" +
                "      <parameters/>\n" +
                "      <options/>\n" +
                "    </org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction>\n" +
                "  </actions>\n" +
                "  <description></description>\n" +
                "  <keepDependencies>false</keepDependencies>\n" +
                "  <properties/>\n" +
                "  <definition class=\"org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition\" plugin=\"workflow-cps@2.61.1\">\n" +
                "    <script>" +

                "    </script>\n" +
                "    <sandbox>true</sandbox>\n" +
                "  </definition>\n" +
                "  <triggers/>\n" +
                "  <disabled>false</disabled>\n" +
                "</flow-definition>";
    }
    public JobConfig(String version, String script) {
        this.version = version;
        this.script = script;
        this.xml = "<?xml version=\'"+this.version+"\' encoding='UTF-8'?>\n" +
                "<flow-definition plugin=\"workflow-job@2.31\">\n" +
                "  <actions>\n" +
                "    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobAction plugin=\"pipeline-model-definition@1.3.4.1\"/>\n" +
                "    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction plugin=\"pipeline-model-definition@1.3.4.1\">\n" +
                "      <jobProperties/>\n" +
                "      <triggers/>\n" +
                "      <parameters/>\n" +
                "      <options/>\n" +
                "    </org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction>\n" +
                "  </actions>\n" +
                "  <description></description>\n" +
                "  <keepDependencies>false</keepDependencies>\n" +
                "  <properties/>\n" +
                "  <definition class=\"org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition\" plugin=\"workflow-cps@2.61.1\">\n" +
                "    <script>\n" +
                        this.script +
                    "</script>\n" +
                "    <sandbox>true</sandbox>\n" +
                "  </definition>\n" +
                "  <triggers/>\n" +
                "  <disabled>false</disabled>\n" +
                "</flow-definition>";
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }
}