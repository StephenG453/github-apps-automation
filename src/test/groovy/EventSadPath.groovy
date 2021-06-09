package groovy

import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification
import static groovyx.net.http.ContentType.*

class EventSadPath extends Specification {

    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://api.github.com/')
    }

    def 'user cant get events with invalid header authentication'() {
        when:
        def response = client.get(path: "events",
                headers: ["User-Agents": "StephenG453"],
                requestContentType: 'application/json'
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 403: 'invalidly retrieved events while using incorrect header'
    }

    def 'user cant get events with invalid header content type'() {
        when:
        def response = client.get(path: "events",
                requestContentType: TEXT,
                headers: ["User-Agent": "StephenG453"]
        )
        then:
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 415: 'invalidly retrieved events while using incorrect content type'
    }
}
