package com.alvinalexander.rssreader

import scala.xml.XML
import scala.xml.Elem
import scalaj.http.{Http, HttpResponse}

object RssReader {

    /**
      * This method returns the RSS feed XML from the URL you supply.
      * @param feedUrl A URL for an RSS feed.
      * @return An instance of scala.xml.Elem that represents the root
      *         node of the RSS feed.
      */
    def getXmlFromRssFeedUrl(
        feedUrl: String,
        connectTimeout: Int = 5000,
        readTimeout: Int = 5000
    ): Elem = {
        val httpResponse = getHttpResponse(feedUrl, connectTimeout, readTimeout)
        val rssFeed = getXmlAsString(httpResponse)
        convertRssFeedStringToXmlElem(rssFeed)
    }

    def getRssFeedFromUrl(feedUrl: String): RssFeed = {
        val rssFeedXml = getXmlFromRssFeedUrl(feedUrl)
        getRssFeedFromXml(rssFeedXml)
    }

    /**
      * If you’ce already downloaded an RSS feed as a String, use this
      * method to parse it into an RssFeed object.
      */
    def getRssFeedFromFeedString(feedAsAString: String): RssFeed = {
        val rssFeedXml = convertRssFeedStringToXmlElem(feedAsAString)
        getRssFeedFromXml(rssFeedXml)
    }

    /**
      * Converts the string representation of RSS to an instance of
      * scala.xml.Elem. You generally only need this if you want to
      * get the channel title, url, description, or lastBuildDate.
      */
    def convertRssFeedStringToXmlElem(feedAsAString: String): Elem = {
        XML.loadString(feedAsAString)
    }

    /**
      * If you want to call the server and get the XML yourself, or just
      * want to test this algorithm, use this method.
      */
    def getRssFeedFromXml(rssFeedXml: Elem): RssFeed = {
        // the list of stories in the rss feed
        val rssItems = (rssFeedXml \\ "item")   //an array of NodeSeq
        val rssFeedItems = for {
            i <- rssItems
            title = (i \ "title").text
            url   = (i \ "link").text
            desc  = (i \ "description").text
        } yield RssFeedStory(title, url, desc)
        RssFeed(
            getChannelTitle(rssFeedXml),
            getChannelUrl(rssFeedXml),
            rssFeedItems
        )
    }

    def getChannelTitle(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "title").text
    def getChannelUrl(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "link").text
    def getChannelDescription(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "description").text
    def getChannelLastBuildDate(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "lastBuildDate").text

    // uses ScalaJ
    private def getHttpResponse(
        url: String,
        connectTimeout: Int = 5000,
        readTimeout: Int = 5000
    ): HttpResponse[String] = {
        Http(url).timeout(connTimeoutMs=connectTimeout, readTimeoutMs=readTimeout).asString
    }

    private def getXmlAsString(httpResponse: HttpResponse[String]): String = httpResponse.body



}
