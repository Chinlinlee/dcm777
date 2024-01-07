package org.github.chinlinlee.dcm777.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import org.dcm4che3.audit.AuditMessage;
import org.dcm4che3.audit.AuditMessages;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.json.JSONReader;
import org.dcm4che3.util.SafeClose;
import org.json.JSONObject;
import org.json.XML;
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

    public static String convertAuditMessageToJsonString(AuditMessage message) throws IOException {
        String xmlString = AuditMessages.toXML(message);
        JSONObject jsonObj = XML.toJSONObject(xmlString);

        return jsonObj.toString();
    }

    public static Map<String, ?> jsonGeneratorFactoryConfig(boolean isIndent) {
        Map<String, ?> conf = new HashMap<String, Object>(2);
        if (isIndent) {
            conf.put(JsonGenerator.PRETTY_PRINTING, null);
        }
        return conf;
    }
}
