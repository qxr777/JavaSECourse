import java.util.ArrayList;
import java.util.Random;

/**
 * The responder class represents a response generator object. It is used
 * to generate an automatic response. This is the second version of this 
 * class. This time, we generate some random behavior by randomly selecting 
 * a phrase from a predefined list of responses.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    0.2 (2016.02.29)
 */
public class Responder
{
    private Random randomGenerator;
    private ArrayList<String> responses;

    /**
     * Construct a Responder
     */
    public Responder()
    {
        randomGenerator = new Random();
        responses = new ArrayList<>();
        fillResponses();
    }

    /**
     * Generate a response.
     * 
     * @return  A string that should be displayed as the response
     */
    public String generateResponse()
    {
        // Pick a random number for the index in the default response 
        // list. The number will be between 0 (inclusive) and the size
        // of the list (exclusive).
        int index = randomGenerator.nextInt(responses.size());
        return responses.get(index);
    }

    /**
     * Build up a list of default responses from which we can pick one
     * if we don't know what else to say.
     */
    private void fillResponses()
    {
        responses.add("That sounds odd. Could you describe this in more detail?");
        responses.add("No other customer has ever complained about this \n" +
                      "before. What is your system configuration?");
        responses.add("I need a bit more information on that.");
        responses.add("Have you checked that you do not have a dll conflict?");
        responses.add("That is covered in the manual. Have you read the manual?");
        responses.add("Your description is a bit wishy-washy. Have you got \n" +
                      "an expert there with you who could describe this better?");
        responses.add("That's not a bug, it's a feature!");
        responses.add("Could you elaborate on that?");
        responses.add("Have you tried running the app on your phone?");
        responses.add("I just checked StackOverflow - they don't know either.");
    }
}

