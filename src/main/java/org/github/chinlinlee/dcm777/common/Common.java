package org.github.chinlinlee.dcm777.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.json.JSONReader;
import org.dcm4che3.util.SafeClose;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class Common {
    public static Attributes getAttributesFromJsonString(String jsonStr) {
        Attributes attr = new Attributes();
        InputStream is;
        is = new ByteArrayInputStream(jsonStr.getBytes(StandardCharsets.UTF_8));
        try {
            JSONReader jsonReader = new JSONReader(
                    Json.createParser(new InputStreamReader(is, StandardCharsets.UTF_8)));
            jsonReader.readDataset(attr);
            return attr;
        } finally {
            if (is != System.in)
                SafeClose.close(is);
        }
    }

    public static void LoadLogConfig(String filename) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            // Call context.reset() to clear any previous configuration, e.g. default
            // configuration. For multi-step configuration, omit calling context.reset().
            context.reset();
            File configFile = new File(filename);
            configurator.doConfigure(configFile);
        } catch (JoranException je) {
            // StatusPrinter will handle this
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }
}
