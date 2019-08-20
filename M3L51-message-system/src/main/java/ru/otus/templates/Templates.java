package ru.otus.templates;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import org.cactoos.BiFunc;

public final class Templates
    implements BiFunc<String, Map<String, Object>, String> {

    private static final String HTML_DIR = "/templates/";
    private static final String ENCODING = "UTF-8";

    private final Configuration config;

    public Templates() throws IOException {
        this.config = new Configuration(Configuration.VERSION_2_3_28);
        this.config.setClassForTemplateLoading(
            this.getClass(),
            Templates.HTML_DIR
        );
        this.config.setDefaultEncoding(
            Templates.ENCODING
        );
    }

    @Override
    public String apply(final String name, final Map<String, Object> data)
        throws Exception {
        try (final Writer stream = new StringWriter()) {
            final Template template = this.config.getTemplate(name);
            template.process(data, stream);
            return stream.toString();
        }
    }
}
