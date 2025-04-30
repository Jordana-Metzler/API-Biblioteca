package br.upf.biblioteca.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_emprestimo")
public class EmprestimoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "emp_livro_id", nullable = false)
    private Long livroId;

    @Temporal(TemporalType.DATE)
    @Column(name = "emp_data_emprestimo")
    private Date dataEmprestimo;

    public EmprestimoDTO() {
    	super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public Long getLivroId() {
        return livroId;
    }
    
    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }
    
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmprestimoDTO other = (EmprestimoDTO) obj;
		return Objects.equals(id, other.id);
	}	
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "EmprestimoDTO{" +
               "id=" + id +
               ", usuarioId=" + usuarioId +
               ", livroId=" + livroId +
               ", dataEmprestimo=" + dataEmprestimo +
               '}';
    }
}
