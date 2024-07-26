package srv.service.helper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ReportService {
    @Cacheable(value = "rep01")
    public JasperReport getReport01() throws JRException {
        InputStream inputStream = getClass().getResourceAsStream("/reports/rep01.jrxml");
        return JasperCompileManager.compileReport(inputStream);
    }
}
