<#ftl output_format="HTML" auto_esc=true>
<html lang="en">
<#include "base.ftl">

<#macro title>Admin Page</#macro>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

<style>
    .min-w-0 { min-width: 0; }
    .user-info { overflow: hidden; }
    .user-name,.user-email { max-width: 100%; }
    .break-anywhere { overflow-wrap: anywhere; word-break: break-word; }
</style>

<#macro content>
    <div class="container mt-5">
        <h2 class="mb-4">User Management</h2>

        <ul class="list-group">
            <#if users?has_content>
                <#list users as user>
                    <li class="list-group-item d-flex align-items-center">
                        <div class="user-info flex-grow-1 min-w-0 me-3">
                            <div class="user-name text-truncate break-anywhere" title="${user.name?html}">
                                ${user.name?html}
                            </div>
                            <div class="user-email text-truncate break-anywhere" title="${user.email?html}">
                                ${user.email?html}
                            </div>
                        </div>

                        <div class="dropdown ms-auto">
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
                                       data-id="${user.id?c}"
                                       data-name="${user.name?html}"
                                       data-email="${user.email?html}">
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

    <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form action="/admin/users/update" method="post" class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateModalLabel">Update User</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        document.addEventListener('show.bs.modal', function (event) {
            if (event.target.id !== 'updateModal') return;
            const btn = event.relatedTarget;
            if (!btn) return;
            document.getElementById('updateUserId').value = btn.getAttribute('data-id');
            document.getElementById('updateName').value  = btn.getAttribute('data-name');
            document.getElementById('updateEmail').value = btn.getAttribute('data-email');
        });
    </script>
</#macro>
</html>
