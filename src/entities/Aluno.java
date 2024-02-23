package entities;

import java.io.Serializable;

public class Aluno implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private int matricula;
    private Curso cursoId;
    private String email;

    public Aluno() {
    }

    public Aluno(String name, int matricula, Curso cursoId, String email) {
        this.name = name;
        this.matricula = matricula;
        this.cursoId = cursoId;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public int getCursoId() {
        return cursoId.getCursoId();
    }
    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + matricula;
        result = prime * result + cursoId.getCursoId();
        return result;
    }
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Aluno other = (Aluno) obj;
        if(email == null){
            if(other.email != null)
                return false;
        } else if(!email.equals(other.email))
            return false;
        if(name == null){
            if(other.name != null)
                return false;
        } else if(!name.equals(other.name))
            return false;
        if(matricula != other.matricula)
            return false;
        if(cursoId != other.cursoId)
            return false;
        return true;
    }
    public String toString(){
        return "Aluno [name=" + name + ", matricula=" + matricula + ", cursoId=" + cursoId + ", email=" + email + "]";
    }

    
}
