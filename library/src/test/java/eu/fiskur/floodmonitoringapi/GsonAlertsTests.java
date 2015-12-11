package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;

import eu.fiskur.floodmonitoringapi.alerts.FloodWarning;

import static org.junit.Assert.assertEquals;

public class GsonAlertsTests {

    @Test
    public void alertsGSONTest() {
        Gson gson = GSONProvider.getRestGson();
        List<FloodWarning> warnings = gson.fromJson(FLOOD_ALERTS, new TypeToken<List<FloodWarning>>() {
        }.getType());
        assertEquals(11, warnings.size());
    }

    @Test
    public void singleAlertGSONTest() {
        Gson gson = GSONProvider.getRestGson();
        FloodWarning warning = gson.fromJson(AN_ALERT, FloodWarning.class);
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/floods/93474", warning.getId());
    }

    private static final String AN_ALERT = "{ \n" +
            "  \"@context\" : \"http://environment.data.gov.uk/flood-monitoring/meta/context.jsonld\" ,\n" +
            "  \"meta\" : { \n" +
            "    \"publisher\" : \"Environment Agency\" ,\n" +
            "    \"licence\" : \"http://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/\" ,\n" +
            "    \"documentation\" : \"http://environment.data.gov.uk/flood-monitoring/doc/reference\" ,\n" +
            "    \"version\" : \"0.5\" ,\n" +
            "    \"comment\" : \"Status: Beta service\" ,\n" +
            "    \"hasFormat\" : [ \"http://environment.data.gov.uk/flood-monitoring/id/floods/93474.rdf\", \"http://environment.data.gov.uk/flood-monitoring/id/floods/93474.ttl\" ]\n" +
            "  }\n" +
            "   ,\n" +
            "  \"items\" : { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93474\" ,\n" +
            "    \"description\" : \"Severn Vyrnwy Confluence\" ,\n" +
            "    \"eaAreaName\" : \"West\" ,\n" +
            "    \"eaRegionName\" : \"Midlands\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/031WAF114\" ,\n" +
            "      \"county\" : \"Shropshire\" ,\n" +
            "      \"currentWarning\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93474\" ,\n" +
            "      \"description\" : \"Severn Vyrnwy Confluence\" ,\n" +
            "      \"eaAreaName\" : \"West\" ,\n" +
            "      \"eaRegionName\" : \"Midlands Region\" ,\n" +
            "      \"envelope\" : { \n" +
            "        \"lowerCorner\" : { \n" +
            "          \"x\" : 323652.897014925 ,\n" +
            "          \"y\" : 314605\n" +
            "        }\n" +
            "         ,\n" +
            "        \"upperCorner\" : { \n" +
            "          \"x\" : 339397.511940298 ,\n" +
            "          \"y\" : 332658\n" +
            "        }\n" +
            "      }\n" +
            "       ,\n" +
            "      \"fwdCode\" : \"031WAF114\" ,\n" +
            "      \"label\" : \"Severn Vyrnwy Confluence\" ,\n" +
            "      \"lat\" : 52.765501928560823 ,\n" +
            "      \"long\" : -2.99314567228076 ,\n" +
            "      \"notation\" : \"031WAF114\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/031WAF114/polygon\" ,\n" +
            "      \"quickDialNumber\" : \"052317\" ,\n" +
            "      \"quickDialNumberWelsh\" : \"0511436\" ,\n" +
            "      \"riverOrSea\" : \"River Severn, River Vyrnwy\" ,\n" +
            "      \"type\" : [ \"http://environment.data.gov.uk/flood-monitoring/def/core/FloodAlertArea\", \"http://environment.data.gov.uk/flood-monitoring/def/core/FloodArea\" ]\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"031WAF114\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"This message contains gauge information for Llanymynech, Maesbrook, Cae Howel and Crew Green. \\n\\nThis message will be updated by 4 pm this evening or if the situation changes.\\n\\nAt 8:30 am today;\\n\\nthe level at the Llanymynech Gauge was 2.19 metres and steady. A peak level of between 3.4 and 3.7 metres is expected to occur this evening.\\nthe level at the Maesbrook Gauge was 0.36 metres and steady. \\nthe level at the Cae Howel Gauge was 3.85 metres and falling slowly. \\nthe level at the Crew Green Gauge was 4.07 metres and falling slowly. \\n\\nRiver levels are expected to rise following overnight rain. \\n\\nThe following roads could be affected by flooding ;\\nCae Howel to Edgerley road.\\n Melverley to Melverley Green road and Ponthen to Melverley road\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-10T09:14:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-10T09:14:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-11-28T16:12:00\" ,\n" +
            "    \"type\" : \"http://environment.data.gov.uk/flood-monitoring/def/core/FloodAlertOrWarning\"\n" +
            "  }\n" +
            "}\n" +
            "\n";

    private static final String FLOOD_ALERTS = "{ \n" +
            "  \"@context\" : \"http://environment.data.gov.uk/flood-monitoring/meta/context.jsonld\" ,\n" +
            "  \"meta\" : { \n" +
            "    \"publisher\" : \"Environment Agency\" ,\n" +
            "    \"licence\" : \"http://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/\" ,\n" +
            "    \"documentation\" : \"http://environment.data.gov.uk/flood-monitoring/doc/reference\" ,\n" +
            "    \"version\" : \"0.5\" ,\n" +
            "    \"comment\" : \"Status: Beta service\" ,\n" +
            "    \"hasFormat\" : [ \"http://environment.data.gov.uk/flood-monitoring/id/floods/.csv\" ]\n" +
            "  }\n" +
            "   ,\n" +
            "  \"items\" : [ \n" +
            "  { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93487\" ,\n" +
            "    \"description\" : \"Upper River Ouse\" ,\n" +
            "    \"eaAreaName\" : \"Yorkshire\" ,\n" +
            "    \"eaRegionName\" : \"North East\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/122WAF946\" ,\n" +
            "      \"county\" : \"North Yorkshire, City of York\" ,\n" +
            "      \"notation\" : \"122WAF946\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/122WAF946/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Ouse, Burdyke, Holgate Beck, Blue Beck, River Foss, The Fleet\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"122WAF946\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"This flood alert remains in force due to high levels on the River Ouse. The current river level at the York Viking Recorder is 3.25m or 10 feet 8 inches and is continuing to fall slowly, however levels will begin to rise this evening as the river responds to recent rainfall. Our Incident Response teams are on 24 hour duty and we will update this message as new forecasts become available.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-10T14:14:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-10T14:14:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-11-29T11:06:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93488\" ,\n" +
            "    \"description\" : \"The Lower River Nidd\" ,\n" +
            "    \"eaAreaName\" : \"Yorkshire\" ,\n" +
            "    \"eaRegionName\" : \"North East\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/122WAF933\" ,\n" +
            "      \"county\" : \"North Yorkshire\" ,\n" +
            "      \"notation\" : \"122WAF933\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/122WAF933/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Nidd\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"122WAF933\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"This flood alert remains in force due to high levels on the River Nidd. The current level at Skip Bridge is 2.9m and will remain high. Our Incident Response teams are on 24 hour duty and we will update this message as new forecasts become available.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-10T12:09:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-10T12:09:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-11-29T11:18:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93504\" ,\n" +
            "    \"description\" : \"River Ouse at Naburn Lock\" ,\n" +
            "    \"eaAreaName\" : \"Yorkshire\" ,\n" +
            "    \"eaRegionName\" : \"North East\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/122FWF723\" ,\n" +
            "      \"county\" : \"North Yorkshire\" ,\n" +
            "      \"notation\" : \"122FWF723\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/122FWF723/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Ouse\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"122FWF723\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"The flood warning for the River Ouse at Naburn Lock remains in force due to high levels on the River Ouse. The current level at Naburn is 7.72m and is expected to remain high. The current river level at the York Viking Recorder is 3.25m or 10 feet 8 inches. Levels in York are falling slowly however are expected to rise again later today as the river responds to recent rainfall. Our Incident Response teams are on 24 hour duty and we will update this message as new forecasts become available.\" ,\n" +
            "    \"severity\" : \"Flood Warning\" ,\n" +
            "    \"severityLevel\" : 2 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-10T14:22:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-10T14:22:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-11-29T20:04:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93548\" ,\n" +
            "    \"description\" : \"The River Dee Catchment in England from Whitchurch to Chester\" ,\n" +
            "    \"eaAreaName\" : \"South\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013WAFDEE\" ,\n" +
            "      \"county\" : \"Cheshire\" ,\n" +
            "      \"notation\" : \"013WAFDEE\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013WAFDEE/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Dee\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"013WAFDEE\" ,\n" +
            "    \"isTidal\" : true ,\n" +
            "    \"message\" : \"Levels on the River Dee are still high and are likely to remain high for the next several days. Further rainfall is forecast over the weekend.  Townfield Lane remains closed. We will continue to monitor the situation and update as required.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-10T10:21:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-10T10:21:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-11-30T15:43:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93602\" ,\n" +
            "    \"description\" : \"English Dee from Shocklach to Chester\" ,\n" +
            "    \"eaAreaName\" : \"South\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013FWFCH50\" ,\n" +
            "      \"county\" : \"Cheshire\" ,\n" +
            "      \"notation\" : \"013FWFCH50\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/013FWFCH50/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Dee\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"013FWFCH50\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"Levels on the River Dee are still high and are likely to remain high for the next several days. Further rainfall is forecast over the weekend.  Townfield Lane remains closed. We will continue to monitor the situation and update as required.\" ,\n" +
            "    \"severity\" : \"Flood Warning\" ,\n" +
            "    \"severityLevel\" : 2 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-10T10:21:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-10T10:21:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-12-02T10:57:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93622\" ,\n" +
            "    \"description\" : \"Rivers Greta, St Johns Beck and Bassenthwaite Lake\" ,\n" +
            "    \"eaAreaName\" : \"North\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFGB\" ,\n" +
            "      \"county\" : \"Cumbria\" ,\n" +
            "      \"notation\" : \"011WAFGB\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFGB/polygon\" ,\n" +
            "      \"riverOrSea\" : \"Greta, St Johns Beck and Glendermackin\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"011WAFGB\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"River and lake levels remain high following heavy rainfall over the weekend. Further rainfall is forecast through Wednesday and overnight into the early hours of Thursday. We expect river and lake levels to rise as a result of this rainfall, however they are forecast to peak well below the levels experienced over the weekend. Our Incident Response teams are on 24 hour duty and we are continuing to monitor the situation closely. We advise that you remain vigilant and visit our web pages or call Floodline for the latest information.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-09T09:48:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-09T09:48:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-12-04T15:27:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93621\" ,\n" +
            "    \"description\" : \"Middle River Eden\" ,\n" +
            "    \"eaAreaName\" : \"North\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFME\" ,\n" +
            "      \"county\" : \"Cumbria\" ,\n" +
            "      \"notation\" : \"011WAFME\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFME/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Eden\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"011WAFME\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"River levels remain high following heavy rainfall over the weekend. Further rainfall is forecast through Wednesday and overnight into the early hours of Thursday. We expect river levels to rise as a result of this rainfall, however they are forecast to peak well below the levels experienced over the weekend. Our Incident Response teams are on 24 hour duty and we are continuing to monitor the situation closely. We advise that you remain vigilant and visit our web pages or call Floodline for the latest information.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-09T09:52:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-09T09:52:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-12-04T15:10:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93620\" ,\n" +
            "    \"description\" : \"Upper River Eden\" ,\n" +
            "    \"eaAreaName\" : \"North\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFUE\" ,\n" +
            "      \"county\" : \"Cumbria\" ,\n" +
            "      \"notation\" : \"011WAFUE\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFUE/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Eden\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"011WAFUE\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"River levels remain high following heavy rainfall over the weekend. Further rainfall is forecast through Wednesday and overnight into the early hours of Thursday. We expect river levels to rise as a result of this rainfall, however they are forecast to peak well below the levels experienced over the weekend. Our Incident Response teams are on 24 hour duty and we are continuing to monitor the situation closely. We advise that you remain vigilant and visit our web pages or call Floodline for the latest information.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-09T09:49:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-09T09:49:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-12-04T15:15:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93623\" ,\n" +
            "    \"description\" : \"Rivers Kent and Bela\" ,\n" +
            "    \"eaAreaName\" : \"North\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFKB\" ,\n" +
            "      \"county\" : \"Cumbria\" ,\n" +
            "      \"notation\" : \"011WAFKB\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFKB/polygon\" ,\n" +
            "      \"riverOrSea\" : \"Rivers Kent and Bela\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"011WAFKB\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"River levels remain high following heavy rainfall over the weekend. Further rainfall is forecast through Wednesday and overnight into the early hours of Thursday. We expect river levels to rise as a result of this rainfall, however they are forecast to peak well below the levels experienced over the weekend. Our Incident Response teams are on 24 hour duty and we are continuing to monitor the situation closely. We advise that you remain vigilant and visit our web pages or call Floodline for the latest information.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-09T09:49:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-09T09:49:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-12-04T15:22:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93624\" ,\n" +
            "    \"description\" : \"Lower River Eden\" ,\n" +
            "    \"eaAreaName\" : \"North\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFLE\" ,\n" +
            "      \"county\" : \"Cumbria\" ,\n" +
            "      \"notation\" : \"011WAFLE\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFLE/polygon\" ,\n" +
            "      \"riverOrSea\" : \"River Eden\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"011WAFLE\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"River levels remain high following heavy rainfall over the weekend. Further rainfall is forecast through Wednesday and overnight into the early hours of Thursday. We expect river levels to rise as a result of this rainfall, however they are forecast to peak well below the levels experienced over the weekend. Our Incident Response teams are on 24 hour duty and we are continuing to monitor the situation closely. We advise that you remain vigilant and visit our web pages or call Floodline for the latest information.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-09T09:51:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-09T09:51:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-12-03T14:47:00\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floods/93626\" ,\n" +
            "    \"description\" : \"Rivers Cocker, Marron and Derwent\" ,\n" +
            "    \"eaAreaName\" : \"North\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"floodArea\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFCD\" ,\n" +
            "      \"county\" : \"Cumbria\" ,\n" +
            "      \"notation\" : \"011WAFCD\" ,\n" +
            "      \"polygon\" : \"http://environment.data.gov.uk/flood-monitoring/id/floodAreas/011WAFCD/polygon\" ,\n" +
            "      \"riverOrSea\" : \"Rivers Cocker Marron and Derwent\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"floodAreaID\" : \"011WAFCD\" ,\n" +
            "    \"isTidal\" : false ,\n" +
            "    \"message\" : \"River and lake levels remain high following heavy rainfall over the weekend. Further rainfall is forecast through Wednesday and overnight into the early hours of Thursday. We expect river and lake levels to rise as a result of this rainfall, however they are forecast to peak well below the levels experienced over the weekend. Our Incident Response teams are on 24 hour duty and we are continuing to monitor the situation closely. We advise that you remain vigilant and visit our web pages or call Floodline for the latest information.\" ,\n" +
            "    \"severity\" : \"Flood Alert\" ,\n" +
            "    \"severityLevel\" : 3 ,\n" +
            "    \"timeMessageChanged\" : \"2015-12-09T09:52:00\" ,\n" +
            "    \"timeRaised\" : \"2015-12-09T09:52:00\" ,\n" +
            "    \"timeSeverityChanged\" : \"2015-12-04T15:30:00\"\n" +
            "  }\n" +
            "   ]\n" +
            "}\n" +
            "\n";
}
