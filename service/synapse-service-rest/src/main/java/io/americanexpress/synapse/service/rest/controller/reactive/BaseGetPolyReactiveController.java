/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.service.rest.controller.reactive;

import io.americanexpress.synapse.service.rest.controller.BaseController;
import io.americanexpress.synapse.service.rest.model.BaseServiceResponse;
import io.americanexpress.synapse.service.rest.service.reactive.BaseGetPolyReactiveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

/**
 * {@code BaseGetPolyReactiveController} is base class for read poly controller.
 *    This controller handles GET method requests, but specifically for read purposes.
 *    This controller returns multiple object.
 * @param <O>
 * @param <S>
 */
public abstract class BaseGetPolyReactiveController<O extends BaseServiceResponse, S extends BaseGetPolyReactiveService<O>> extends BaseController<S> {

    /**
     *
     * Get a list of multiple resources from the back end service.
     * @return
     */
    @ApiOperation(value = "Reactive get flux", notes = "Gets all resources reactively")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @GetMapping()
    public Flux<ResponseEntity<O>> read() {
        logger.entry();

        final var serviceResponse = service.read();
        final var responseEntity = serviceResponse
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());

        logger.exit();
        return responseEntity;
    }
}