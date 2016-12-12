package org.nzbhydra.fortests;

import org.nzbhydra.api.ApiCallParameters;
import org.nzbhydra.mapping.*;
import org.nzbhydra.mapping.NewznabResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NewznabResponseBuilder {


    private static int numberOfDifferentTitles = 10;
    private static int numberOfDuplicatesPerTitle = 2;

    @RequestMapping(value = "/tests/api", produces = MediaType.TEXT_XML_VALUE)
    public RssRoot getTestResult(ApiCallParameters parameters) {

        RssRoot rssRoot = new RssRoot();
        rssRoot.setVersion("2.0");
        RssChannel channel = new RssChannel();
        channel.setTitle("channelTitle");
        channel.setDescription("channelDescription");
        channel.setLanguage("en-gb");
        channel.setWebMaster("webmaster@master.com");
        channel.setLink("http://www.link.xyz");
        channel.setNewznabResponse(new NewznabResponse(0, 100));

        List<RssItem> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {

            RssItem item = new RssItem();
            item.setDescription("Some longer itemDescription that whatever" + i);
            item.setTitle("itemTitle" + i);
            item.setPubDate(Instant.ofEpochSecond(1000));
            item.setEnclosure(new Enclosure("enclosureUrl", 5L));
            item.setComments("http://www.comments.com/" + i);
            item.setLink("http://www.link.com/" + i);
            item.setCategory("category");
            item.setRssGuid(new RssGuid("http://www.guid.com/" + i, true));

            List<NewznabAttribute> attributes = new ArrayList<>();
            attributes.add(new NewznabAttribute("category", "7000"));
            attributes.add(new NewznabAttribute("size", "5"));
            attributes.add(new NewznabAttribute("guid", "attributeGuid" + i));
            attributes.add(new NewznabAttribute("poster", "poster"));
            attributes.add(new NewznabAttribute("group", "group"));
            item.setAttributes(attributes);

            items.add(item);
        }
        channel.setItems(items);

        rssRoot.setRssChannel(channel);

        return rssRoot;
    }


}
