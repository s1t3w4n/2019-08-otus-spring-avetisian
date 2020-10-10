package ru.otus.hw16.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw16.models.Author;
import ru.otus.hw16.models.Book;
import ru.otus.hw16.models.Comment;
import ru.otus.hw16.models.Genre;
import ru.otus.hw16.repositories.AuthorRepository;
import ru.otus.hw16.repositories.BookRepository;
import ru.otus.hw16.repositories.CommentRepository;
import ru.otus.hw16.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final long NO_ID = 0;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final MutableAclService mutableAclService;

    public LibraryServiceImpl(BookRepository bookRepository,
                              AuthorRepository authorRepository,
                              GenreRepository genreRepository,
                              CommentRepository commentRepository,
                              MutableAclService mutableAclService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.mutableAclService = mutableAclService;
    }

    @Transactional
    @Override
    public Book createBook(String tittle, String firstName, String lastName, String genre) {
        return bookRepository.save(
                new Book(NO_ID,
                        tittle,
                        checkForAuthor(firstName, lastName),
                        checkForGenre(genre)));
    }

//    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    @Override
    public Optional<Book> readById(long id) {
        return bookRepository.findById(id);
    }

//    @PostAuthorize("hasAuthority('ADMIN')")
    @Transactional
    @Override
    public Book updateBook(long id, String tittle, String firstName, String lastName, String genre) {
        return bookRepository.save(
                new Book(id, tittle, checkForAuthor(firstName, lastName), checkForGenre(genre)));
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    @Override
    public void deleteById(long id) {
        final Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            commentRepository.deleteCommentByBook(book.get());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> readAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Long> getAllBooksIDs() {
        return bookRepository.getAllBookIDs();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllFirstNames() {
        return authorRepository.getAllFirstNames();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllLastNames() {
        return authorRepository.getAllLastNames();
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllGenre() {
        return genreRepository.getAllGenre();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getBookComments(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Transactional
    @Override
    public void leaveCommentToBook(Book book, String text) {
        final Comment comment = commentRepository.save(new Comment(NO_ID, text, book));
        final Sid owner = new PrincipalSid(SecurityContextHolder.getContext().getAuthentication());
        final ObjectIdentity oid = new ObjectIdentityImpl(comment.getClass(), comment.getId());
        final MutableAcl acl = mutableAclService.createAcl(oid);
        acl.setOwner(owner);
        acl.insertAce(acl.getEntries().size(), BasePermission.READ, owner, true);
        mutableAclService.updateAcl(acl);
    }

    @PostFilter("hasPermission(filterObject, 'READ')")
    @Transactional(readOnly = true)
    @Override
    public List<Comment> readAllComments() {
        return commentRepository.findAll();
    }

    private Author checkForAuthor(String firstName, String lastName) {
        return authorRepository
                .getByFirstNameAndLastName(firstName, lastName)
                .orElseGet(() -> authorRepository.save(new Author(NO_ID, firstName, lastName)));
    }

    private Genre checkForGenre(String genre) {
        return genreRepository
                .findByGenre(genre)
                .orElseGet(() -> genreRepository.save(new Genre(NO_ID, genre)));
    }
}
