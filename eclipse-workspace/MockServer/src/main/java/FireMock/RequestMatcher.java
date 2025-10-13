package FireMock;

import java.util.List;
import spark.Request;

public class RequestMatcher {

    public static Mapping findMatch(Request req, List<Mapping> mappings) {
        String body = req.body() != null ? req.body().replaceAll("\\s","") : "";

        
        for (Mapping m : mappings) {
            String bodyContains = m.getRequest().getBodyContains();

            if(req.requestMethod().equalsIgnoreCase(m.getRequest().getMethod())
               && req.pathInfo().equals(m.getRequest().getUrl())) {

                if(bodyContains != null && !bodyContains.isEmpty() && body.contains(bodyContains)) {
                    return m; 
                }
            }
        }

        for (Mapping m : mappings) {
            if(req.requestMethod().equalsIgnoreCase(m.getRequest().getMethod())
               && req.pathInfo().equals(m.getRequest().getUrl())
               && (m.getRequest().getBodyContains() == null || m.getRequest().getBodyContains().isEmpty())) {
                return m; 
            }
        }

        
        return null;
    }
}
