package srv.domains.invoice.master;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ReportService {
    @Cacheable(value = "invoice")
    public JasperReport getInvoice() throws JRException {
        InputStream inputStream = getClass().getResourceAsStream("/reports/invoice.jrxml");
        return JasperCompileManager.compileReport(inputStream);
    }
}
