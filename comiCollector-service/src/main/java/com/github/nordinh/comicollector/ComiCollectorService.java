package com.github.nordinh.comicollector;

import com.github.nordinh.comicollector.config.*;


import com.github.nordinh.comicollector.resources.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Optional;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComiCollectorService extends Application<ComiCollectorConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(ComiCollectorService.class);

    public static void main(String[] args) throws Exception {
        new ComiCollectorService().run(args);
    }

    private final HibernateBundle<ComiCollectorConfiguration> hibernateBundle = new HibernateBundle<ComiCollectorConfiguration>(
            
            Void.class
        ) {
        @Override
        public DataSourceFactory getDataSourceFactory(ComiCollectorConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "comiCollector";
    }

    @Override
    public void initialize(Bootstrap<ComiCollectorConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/app/", "/", "index.html"));
        bootstrap.addBundle(hibernateBundle);
        ObjectMapper mapper = bootstrap.getObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(ComiCollectorConfiguration configuration,
                    Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/comiCollector/*");
        
        environment.jersey().register(new CollectedSeriesResource());
        
    }
}
