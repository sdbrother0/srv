package srv.domains.invoice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import srv.domains.customer.CustomerEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime created;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

    @Formula(value="(SELECT coalesce(sum((inv_d.tax + inv_d.price) * inv_d.quantity), 0) FROM invoice_product_details inv_d WHERE inv_d.invoice_id = id)")
    private Long total;

    @Formula(value="(SELECT coalesce(sum(inv_d.tax * inv_d.quantity), 0) FROM invoice_product_details inv_d WHERE inv_d.invoice_id = id)")
    private Long taxTotal;

}
