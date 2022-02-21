package com.appllication.teluslibrary.Entity;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LOAN")
public class Loan 
{
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
    @JoinColumn(name = "fk_book")
    Book fk_book;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    User fk_user;
    
    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "penalization")
    private Double penalization;
    
    @Column(name = "status")
    private String status;
}
