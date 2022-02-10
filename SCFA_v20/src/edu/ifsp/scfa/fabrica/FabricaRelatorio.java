
package edu.ifsp.scfa.fabrica;

import edu.ifsp.scfa.util.Conexao;
import java.io.InputStream;
import java.util.HashMap;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

public class FabricaRelatorio {
    
    public void gerar(EntityManager em, String retlatorio) throws JRException{
        
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,em);
        //parameters.put("cliente","zorro")
        
     InputStream jasperReport = getClass().getResourceAsStream(retlatorio);
     JasperFillManager.fillReport(jasperReport, parameters);
    
    }
    
}
