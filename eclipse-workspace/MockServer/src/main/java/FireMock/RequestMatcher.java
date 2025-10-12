/*package FireMock;

import java.util.List;
import spark.Request;
public class RequestMatcher {
	public static Mapping findMatch(Request req,List<Mapping> mappings) {
		for(Mapping m:mappings) {
			if(req.requestMethod().equalsIgnoreCase(m.getRequest().getMethod()) && req.url().equals(m.getRequest().getUrl())){
				String bodyContains=m.getRequest().getBodyContains();
				if(bodyContains == null || req.body().contains(bodyContains)) {
					return m;
				}
			}
		}
		return null;
		}
		
	}*/

package FireMock;

import java.util.List;
import spark.Request;

public class RequestMatcher {

    public static Mapping findMatch(Request req, List<Mapping> mappings) {
        for(Mapping m : mappings) {
            // Use pathInfo() instead of url()
            if(req.requestMethod().equalsIgnoreCase(m.getRequest().getMethod()) 
               && req.pathInfo().equals(m.getRequest().getUrl())) {

                String bodyContains = m.getRequest().getBodyContains();
                if(bodyContains == null || req.body().contains(bodyContains)) {
                    return m;
                }
            }
        }
        return null;
    }
}
