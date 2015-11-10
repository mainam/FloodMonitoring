package eu.fiskur.floodmonitoringapi.model;

public class Meta {
    String publisher;
    String licence;
    String documentation;
    String version;
    String comment;
    String[] hasFormat;


    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String[] getHasFormat() {
        return hasFormat;
    }

    public void setHasFormat(String[] hasFormat) {
        this.hasFormat = hasFormat;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(publisher != null){
            sb.append("publisher: " + publisher + "\n");
        }
        if(licence != null){
            sb.append("licence: " + licence + "\n");
        }
        if(documentation != null){
            sb.append("documentation: " + documentation + "\n");
        }
        if(version != null){
            sb.append("version: " + version + "\n");
        }
        if(comment != null){
            sb.append("comment: " + comment + "\n");
        }
        if(hasFormat != null){
            for(String format : hasFormat){
                sb.append("hasFormat: " + hasFormat);
            }
        }

        return sb.toString();
    }
}
