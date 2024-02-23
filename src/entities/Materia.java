package entities;

public class Materia {
    private String name;
    private int materiaId;
    private Curso cursoId;

    public Materia() {
    }

    public Materia(String name, int materiaId, Curso cursoId) {
        this.name = name;
        this.materiaId = materiaId;
        this.cursoId = cursoId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMateriaId() {
        return materiaId;
    }
    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }
    public int getCursoId() {
        return cursoId.getCursoId();
    }
    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + materiaId;
        result = prime * result + cursoId.getCursoId();
        return result;
    }
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Materia other = (Materia) obj;
        if(name == null){
            if(other.name != null)
                return false;
        } else if(!name.equals(other.name))
            return false;
        if(materiaId != other.materiaId)
            return false;
        if(cursoId != other.cursoId)
            return false;
        return true;
    }
    public String toString()
    {
        return "Materia [name=" + name + ", materiaId=" + materiaId + ", cursoId=" + cursoId + "]";
    }

}
