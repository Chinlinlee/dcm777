package org.github.chinlinlee.dcm777.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.json.JSONReader;
import org.dcm4che3.util.SafeClose;

public class Common {
    public static Attributes getAttributesFromJsonString(String jsonStr) {
        Attributes attr = new Attributes();
        InputStream is;
        is = new ByteArrayInputStream(jsonStr.getBytes(StandardCharsets.UTF_8));
        try {
            JSONReader jsonReader = new JSONReader(Json.createParser(new InputStreamReader(is, StandardCharsets.UTF_8)));
            jsonReader.readDataset(attr);
            return attr;
        } finally {
            if (is != System.in)
                SafeClose.close(is);
        }
    }
}
