package ucalgary.stbig.com.ucalgary.listeners;


import ucalgary.stbig.com.ucalgary.objects.Cursos;
import ucalgary.stbig.com.ucalgary.objects.Events;
import ucalgary.stbig.com.ucalgary.objects.News;
import ucalgary.stbig.com.ucalgary.objects.SSC;

/**
 * Created by emedinaa on 07/04/2015.
 */
public interface OnHomeListener {

    public void selectedListerNews(News news);
    public void selectedListerSSC(SSC scc);
    public void selectedListerCursos(Cursos courses);
    public void selectedListerEvents(Events events);


    public void backNews();
    public void backSSC();
    public void backCursos();
    public void backEvents();
    public void backSubscription();

}
