package com.example.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        List<String> autoConfigs = new ArrayList<>();

        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
//        for(String candidate : candidates){
//            autoConfigs.add(candidate);
//        }

        candidates.forEach(autoConfigs::add);

        //return StreamSupport.stream(candidates.spliterator(),false).toArray(String[]::new);
        //return autoConfigs.stream().toArray(String[]::new);
        return autoConfigs.toArray(new String[0]);


//        return new String[] {
//                "com.example.config.autoconfig.DispatcherServletConfig",
//                "com.example.config.autoconfig.TomcatWebServerConfig"
//        };
    }
}
