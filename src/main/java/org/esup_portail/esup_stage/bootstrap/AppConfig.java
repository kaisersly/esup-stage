package org.esup_portail.esup_stage.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class AppConfig {
    private String casUrlLogin;
    private String casUrlLogout;
    private String casUrlService;
    private String datasourceUrl;
    private String datasourceUsername;
    private String datasourcePassword;
    private String datasourceDriver;
    private String url;
    private List<String> adminTechs = new ArrayList<>();
    private String referentielWsLogin;
    private String referentielWsPassword;
    private String referentielWsLdapUrl;
    private String referentielWsApogeeUrl;

    public String getCasUrlLogin() {
        return casUrlLogin;
    }

    public void setCasUrlLogin(String casUrlLogin) {
        this.casUrlLogin = casUrlLogin;
    }

    public String getCasUrlLogout() {
        return casUrlLogout;
    }

    public void setCasUrlLogout(String casUrlLogout) {
        this.casUrlLogout = casUrlLogout;
    }

    public String getCasUrlService() {
        return casUrlService;
    }

    public void setCasUrlService(String casUrlService) {
        this.casUrlService = casUrlService;
    }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public void setDatasourceUrl(String datasourceUrl) {
        this.datasourceUrl = datasourceUrl;
    }

    public String getDatasourceUsername() {
        return datasourceUsername;
    }

    public void setDatasourceUsername(String datasourceUsername) {
        this.datasourceUsername = datasourceUsername;
    }

    public String getDatasourcePassword() {
        return datasourcePassword;
    }

    public void setDatasourcePassword(String datasourcePassword) {
        this.datasourcePassword = datasourcePassword;
    }

    public String getDatasourceDriver() {
        return datasourceDriver;
    }

    public void setDatasourceDriver(String datasourceDriver) {
        this.datasourceDriver = datasourceDriver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getAdminTechs() {
        return adminTechs;
    }

    public void setAdminTechs(List<String> adminTechs) {
        this.adminTechs = adminTechs;
    }

    public String getReferentielWsLogin() {
        return referentielWsLogin;
    }

    public void setReferentielWsLogin(String referentielWsLogin) {
        this.referentielWsLogin = referentielWsLogin;
    }

    public String getReferentielWsPassword() {
        return referentielWsPassword;
    }

    public void setReferentielWsPassword(String referentielWsPassword) {
        this.referentielWsPassword = referentielWsPassword;
    }

    public String getReferentielWsLdapUrl() {
        return referentielWsLdapUrl;
    }

    public void setReferentielWsLdapUrl(String referentielWsLdapUrl) {
        this.referentielWsLdapUrl = referentielWsLdapUrl;
    }

    public String getReferentielWsApogeeUrl() {
        return referentielWsApogeeUrl;
    }

    public void setReferentielWsApogeeUrl(String referentielWsApogeeUrl) {
        this.referentielWsApogeeUrl = referentielWsApogeeUrl;
    }

    public void initProperties(Properties props, String prefixeProps) {
        this.casUrlLogout = props.getProperty("cas.url.logout");
        this.casUrlLogin = props.getProperty("cas.url.login");
        this.casUrlService = props.getProperty("cas.url.service");

        this.datasourceUrl = props.getProperty(prefixeProps+"datasource.url");
        this.datasourceUsername = props.getProperty(prefixeProps+"datasource.username");
        this.datasourcePassword = props.getProperty(prefixeProps+"datasource.password");
        this.datasourceDriver = props.getProperty(prefixeProps+"datasource.driver");

        this.url = props.getProperty(prefixeProps+"url");
        if (props.containsKey(prefixeProps+"admin_technique")) {
            this.adminTechs = Arrays.asList(props.getProperty(prefixeProps+"admin_technique").split(";"));
        }

        this.referentielWsLogin = props.getProperty("referentiel.ws.login");
        this.referentielWsPassword = props.getProperty("referentiel.ws.password");
        this.referentielWsLdapUrl = props.getProperty("referentiel.ws.ldap_url");
        this.referentielWsApogeeUrl = props.getProperty("referentiel.ws.apogee_url");
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                ", casUrlLogin='" + casUrlLogin + "'" +
                ", casUrlLogout='" + casUrlLogout + "'" +
                ", casUrlService='" + casUrlService + "'" +
                ", datasourceUrl='" + datasourceUrl + "'" +
                ", datasourceUsername='" + datasourceUsername + "'" +
                ", datasourceDriver='" + datasourceDriver + "'" +
                ", url='" + url + "'" +
                ", adminTechs='" + adminTechs + "'" +
                ", referentielWsLogin='" + referentielWsLogin + "'" +
                ", referentielWsLdapUrl='" + referentielWsLdapUrl + "'" +
                ", referentielWsApogeeUrl='" + referentielWsApogeeUrl + "'" +
                "}";
    }
}