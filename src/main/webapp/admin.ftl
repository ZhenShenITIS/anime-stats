<html lang="en">
<#include "base.ftl">

<#macro title>Admin Page</#macro>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
      crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="/WEB-INF/style.csstyle.css">

<#macro content>
    <div class="container mt-5">
        <h2 class="mb-4">User Management</h2>

        <ul class="list-group">
            <#if users?has_content>
                <#list users as user>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                            <div class="fw-semibold">${user.name}</div>
                            <div class="text-muted small">${user.email}</div>
                        </div>

                        <div class="dropdown">
                            <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                Actions
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li class="px-2">
                                    <form action="/admin/users/delete" method="post" class="m-0">
                                        <input type="hidden" name="id" value="${user.id}"/>
                                        <button type="submit" class="dropdown-item text-danger">Delete</button>
                                    </form>
                                </li>
                                <li>
                                    <a href="#" class="dropdown-item update-btn"
                                       data-bs-toggle="modal" data-bs-target="#updateModal"
                                       data-id="${user.id}"
                                       data-name="${user.name}"
                                       data-email="${user.email}">
                                        Update
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </#list>
            <#else>
                <li class="list-group-item">No users found</li>
            </#if>
        </ul>
    </div>

    <!-- Update Modal -->
    <div class="modal fade" id="updateModal" tabindex="-1"
         aria-labelledby="updateModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form action="/admin/users/update" method="post" class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateModalLabel">Update User</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="id" id="updateUserId"/>
                    <div class="mb-3">
                        <label for="updateName" class="form-label">Name</label>
                        <input type="text" class="form-control" id="updateName" name="name" required/>
                    </div>
                    <div class="mb-3">
                        <label for="updateEmail" class="form-label">Email</label>
                        <input type="email" class="form-control" id="updateEmail" name="email" required/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        // Заполняем поля модалки данными пользователя из data-* атрибутов
        document.addEventListener('show.bs.modal', function (event) {
            if (event.target.id !== 'updateModal') return;
            const btn = event.relatedTarget;
            if (!btn) return;
            document.getElementById('updateUserId').value = btn.getAttribute('data-id');
            document.getElementById('updateName').value = btn.getAttribute('data-name');
            document.getElementById('updateEmail').value = btn.getAttribute('data-email');
        });
    </script>
</#macro>
</html>
