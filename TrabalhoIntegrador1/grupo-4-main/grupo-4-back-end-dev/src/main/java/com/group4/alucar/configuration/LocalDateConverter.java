// package com.group4.alucar.configuration;

// import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

// import java.time.format.DateTimeFormatter;

// @Configuration
// public class DateTimeConfiguration {
//     private static final String dateFormat = "yyyy-MM-dd";

//     private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

//     @Bean
//     @ConditionalOnProperty(value = "spring.jackson.date-format", matchIfMissing = true, havingValue = "none")
//     public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//         return new Jackson2ObjectMapperBuilderCustomizer() {
//             @Override
//             public void customize(Jackson2ObjectMapperBuilder builder) {
//                 builder.simpleDateFormat(dateTimeFormat);
//                 builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
//                 builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
//             }
//         };
//     }
// }

// package com.group4.alucar.configuration;

// import java.time.format.DateTimeFormatter;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.format.datetime.DateFormatter;
// import org.springframework.format.datetime.DateFormatterRegistrar;
// import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
// import org.springframework.format.support.DefaultFormattingConversionService;
// import org.springframework.format.support.FormattingConversionService;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// @Configuration
// public class DateTimeConfiguration extends WebMvcConfigurationSupport {

//     @Bean
//     @Override
//     public FormattingConversionService mvcConversionService() {
//         DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

//         DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
//         dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//         dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//         dateTimeRegistrar.registerFormatters(conversionService);

//         DateFormatterRegistrar dateRegistrar = new DateFormatterRegistrar();
//         dateRegistrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
//         dateRegistrar.registerFormatters(conversionService);

//         return conversionService;
//     }
// }

// package com.group4.alucar.configuration;

// import java.time.format.DateTimeFormatter;

// import org.springframework.boot.autoconfigure.AutoConfigureBefore;
// import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
// import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

// import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
// import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

// @Configuration
// @AutoConfigureBefore({ JacksonAutoConfiguration.class })
// public class DateTimeConfiguration {

//     @Bean
//     public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperBuilderCustomizer() {
//         return new Jackson2ObjectMapperBuilderCustomizer() {

//             @Override
//             public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//                 final String dateFormat = "dd-MM-yyyy";
//                 final String timeFormat = "hh:mm";
//                 final String dateTimeFormat = "dd-MM-yyyy HH:mm";
//                 jacksonObjectMapperBuilder
//                         .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)))
//                         .deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)))
//                         .serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)))
//                         .deserializers(new LocalTimeDeserializer(DateTimeFormatter.ofPattern(timeFormat)))
//                         .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)))
//                         .deserializers(
//                                 new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
//             }
//         };
//     }
// }

package com.group4.alucar.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {
    private static final List<String> SUPPORTED_FORMATS = Arrays.asList("dd-MM-yyyy", "yyyy-MM-dd");
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = SUPPORTED_FORMATS
            .stream()
            .map(DateTimeFormatter::ofPattern)
            .collect(Collectors.toList());

    @Override
    public LocalDate convert(String s) {

        for (DateTimeFormatter dateTimeFormatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDate.parse(s, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                // deliberate empty block so that all parsers run
            }
        }

        throw new DateTimeException(String.format("unable to parse (%s) supported formats are %s",
                s, String.join(", ", SUPPORTED_FORMATS)));
    }
}