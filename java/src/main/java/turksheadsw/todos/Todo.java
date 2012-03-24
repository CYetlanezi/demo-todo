package turksheadsw.todos;

public class Todo
{
    private int id;
    private String content;
    private int done;
    private int order;

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public int getDone() { return this.done; }
    public void setDone(int done) { this.done = done; }

    public int getOrder() { return this.order; }
    public void setOrder(int order) { this.order = order; }
}
