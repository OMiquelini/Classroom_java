package entities;

public class Curso {
    private String name;
    private int cursoId;

    public Curso() {
    }

    public Curso(String name, int cursoId) {
        this.name = name;
        this.cursoId = cursoId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCursoId() {
        return cursoId;
    }
    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + cursoId;
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
        Curso other = (Curso) obj;
        if(name == null){
            if(other.name != null)
                return false;
        } else if(!name.equals(other.name))
            return false;
        if(cursoId != other.cursoId)
            return false;
        return true;
    }
    public String toString()
    {
        return "Curso [name=" + name + ", cursoId=" + cursoId + "]";
    }    
}
