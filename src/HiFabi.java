public class HiFabi {
    public float[] getFunctionParameters(float[] coords1, float[] coords2,){
        float[] parameters = new float[2];
        float gradient = (coords1[1]-coords2[1])/(coords1[0]-coords2[0]);
        float hierenglischeswortfuerordinatenabschnitteinsetzen = coords1[1]-(parameters[0]*coords1[0]);
        parameters[0] = gradient;
        parameters[1] = hierenglischeswortfuerordinatenabschnitteinsetzen;
        return parameters;
    }
}
