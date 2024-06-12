package IndexCreation;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.*;
import java.util.*;

public class NounChecker {

    private StanfordCoreNLP pipeline;

    public NounChecker (){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos");
        pipeline = new StanfordCoreNLP(props);
    }

    public boolean isNoun (String token){
        Annotation document = new Annotation(token);
        pipeline.annotate(document);

            for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel word : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = word.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if (pos.startsWith("NN")) {
                    return true;
                }
            }
        }
        return false;
        }
    
    
}
