    package com.TaDuy.microservices.history_analytics_service.Config;


    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.context.annotation.Configuration;

    @Configuration
        public class GenimiConfig {
        @Value("${google.gemini.api-key}")
        private String apiKey;
        @Value("${google.gemini.url}")
        private String url;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
