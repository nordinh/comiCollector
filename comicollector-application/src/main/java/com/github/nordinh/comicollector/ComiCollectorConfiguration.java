package com.github.nordinh.comicollector;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.commercehub.dropwizard.mongo.MongoClientFactory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ComiCollectorConfiguration extends Configuration {
	
	@Valid
    @NotNull
    private MongoClientFactory mongo;

    @JsonProperty
    public MongoClientFactory getMongo() {
        return mongo;
    }

    @JsonProperty
    public void setMongo(MongoClientFactory mongo) {
        this.mongo = mongo;
    }
	
	@NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

}
