package ec.nubesoft.kioskos.restfilters;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.servlet.http.HttpServletRequest;
@Provider
@PreMatching
public class RESTCorsDemoRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger( RESTCorsDemoRequestFilter.class.getName() );
    @Context
    private HttpServletRequest servletRequest;
    @Override
    public void filter( ContainerRequestContext requestCtx ) throws IOException {
        log.info( "Executing REST request filter "+servletRequest.getRemoteAddr() );

        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if ( requestCtx.getRequest().getMethod().equals( "OPTIONS" ) ) {
            log.info( "HTTP Method (OPTIONS) - Detected!" );

            // Just send a OK signal back to the browser
            requestCtx.abortWith( Response.status( Response.Status.OK ).build() );
        }
    }
}