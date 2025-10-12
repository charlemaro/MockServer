package FireMock;
import spark.Spark;
import java.util.*;

public class MockServer {
		private final int  port;
		private final List<Mapping> mappings;
		
		 public MockServer(int port) {
			this.port=port;
			this.mappings=MappingLoader.loadMappings("/MockServer/src/main/resources/mappings");
		}
		public void start() {
			Spark.port(port);
			Spark.post("/*", (req,res)->{
				Mapping match=RequestMatcher.findMatch(req, mappings);
				if(match != null) {
					res.status(match.getResponse().getStatus());
					res.type("application/json");
					return match.getResponse().getBody();
				}else {
					res.status(400);
					return "{\"error\":\"No matcching record found\"}";
				}
			});

    }
}

