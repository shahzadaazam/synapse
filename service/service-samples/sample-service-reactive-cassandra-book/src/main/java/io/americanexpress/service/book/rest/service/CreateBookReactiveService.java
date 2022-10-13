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
package io.americanexpress.service.book.rest.service;

import io.americanexpress.data.book.entity.BookEntity;
import io.americanexpress.data.book.repository.BookRepository;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.service.helper.BookEntityCreator;
import io.americanexpress.synapse.service.rest.service.reactive.BaseCreateReactiveService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * {@code CreateBookReactiveService} is the service class for creating a book in the Cassandra Book database.
 */
@Service
public class CreateBookReactiveService extends BaseCreateReactiveService<CreateBookRequest, CreateBookResponse> {

    private final BookRepository bookRepository;

    /**
     * Instantiates a new CreateBookReactiveService.
     *
     * @param bookRepository the book repository
     */
    public CreateBookReactiveService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    protected Mono<CreateBookResponse> executeCreate(CreateBookRequest request) {
        BookEntity bookEntity = BookEntityCreator.create(request.getTitle(), request.getAuthor(), 1);
        return bookRepository.save(bookEntity).map(book -> new CreateBookResponse()).switchIfEmpty(Mono.empty());
    }
}
