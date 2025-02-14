package org.example.openAI;

import org.example.model.dto.audio.AudioRequestDto;
import org.example.model.dto.openai.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "OpenAiAssistantsClient", url = "https://api.openai.com/v1", configuration = AssistantHeaderConfiguration.class)
public interface AssistantsClient {
    @PostMapping("/assistants")
    AssistantResponseDto createAssistants(@RequestBody AssistantsRequestDto assistantsRequestDto);

    //코드 인터프리터가 적용된 어시스턴트에만 파일 attach 됨..
    @PostMapping("/assistants/{assistantId}/files")
    AssistantFileResponseDto attachFileToAssistant(@PathVariable("assistantId") String assistantId, @RequestBody AssistantFileRequestDto assistantFileRequestDto);

    @GetMapping("/assistants")
    AssistantsListResponseDto getAssistantsList();

    @GetMapping("/assistants/{assistantId}")
    AssistantResponseDto searchAssistant(@PathVariable("assistantId") String assistantId);

    @PostMapping("/assistants/{assistantId}")
    AssistantResponseDto modifyAssistant(@PathVariable("assistantId") String assistantId, @RequestBody AssistantModifyRequestDto assistantModifyRequestDto);

    @DeleteMapping("/assistants/{assistantId}")
    ResponseEntity<Object> deleteAssistant(@PathVariable("assistantId") String assistantId);

    @DeleteMapping("/assistants/{assistantId}/files/{fileId}")
    ResponseEntity<Object> deleteAssistantFile(@PathVariable("assistantId") String assistantId, @PathVariable("fileId") String fileId);

    @PostMapping("/threads")
    ThreadsResponseDto createThreads();

    @DeleteMapping("/threads/{threadId}")
    ThreadsDeleteResponseDto deleteThreads(@PathVariable("threadId") String threadId);

    @PostMapping("/threads/{threadId}/messages")
    MessagesResponseDto createMessages(@PathVariable("threadId") String threadId, @RequestBody MessagesRequestDto messagesRequestDto);

    @GetMapping("/threads/{threadId}/messages")
    MessagesListResponseDto getMessagesList(@PathVariable("threadId") String threadId);

    @PostMapping("/threads/{threadId}/runs")
    RunsResponseDto createRuns(@PathVariable("threadId") String threadId, @RequestBody RunsRequestDto runsRequestDto);

    @GetMapping("/threads/{threadId}/runs")
    RunsListResponseDto getRunList(@PathVariable("threadId") String threadId);

    @GetMapping("/threads/{threadId}/runs/{runId}")
    RunsResponseDto getRun(@PathVariable("threadId") String threadId, @PathVariable("runId") String runId);

    //@PostMapping(value = "/audio/speech", produces = "audio/mpeg")
    //byte[] createSpeech(@RequestBody AudioRequestDto audioRequestDto);

    @PostMapping(value = "/audio/speech", produces = "audio/mpeg")
    byte[] createSpeech(@RequestBody AudioRequestDto audioRequestDto);

}