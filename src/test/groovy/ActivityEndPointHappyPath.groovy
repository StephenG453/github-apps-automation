import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class ActivityEndPointHappyPath extends Specification {

    @Shared def client

    def setupSpec() {
        client = new RESTClient('https://api.github.com/')
    }

    def 'user can get events'() {
        when:
        def response = client.get(path: "events",
                headers: ["User-Agent": "StephenG453"],
                requestContentType: 'application/json'
        )
        System.out.println("data:\n" + response.data)
        System.out.println("headers:\n" + response.headers)
        then:
        assert response.status == 200: 'could not retrieve events'
        assert response.contentType == 'application/json': 'invalid content type specified'
        //if I knew more about this API to know exactly what the data in the response means we could do
        // multiple assertions regarding that
    }

    def 'user can get events for a network of repositories'() {
        when:
        def response = client.get(path: "networks/octocat/hello-world/events",
                headers: ["User-Agent": "StephenG453"],
                requestContentType: 'application/json'
        )
        then:
        assert response.status == 200: 'could not retrieve events for a network of repositories'
        assert response.contentType == 'application/json': 'invalid content type specified'
        //if I knew more about this API to know exactly what the data in the response means we could do
        // multiple assertions regarding that
    }

}
