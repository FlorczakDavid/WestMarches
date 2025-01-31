describe('My First Test', () => {
  it('Gets, types and asserts', () => {
    cy.visit('http://localhost:3000');

    // cy.contains('type').click()

    // // Should be on a new URL which
    // // includes '/commands/actions'
    // cy.url().should('include', '/commands/actions')

    // // Get an input, type into it
    cy.get('[data-cy="event-mode"]').click();
    cy.get('[data-cy="cell"]').click({ multiple: true });

    // //  Verify that the value has been updated
    // cy.get('.action-email').should('have.value', 'fake@email.com')
  })
})