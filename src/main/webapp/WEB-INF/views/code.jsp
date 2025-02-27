<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-lg-6 col-xl-4 offset-lg-3 offset-xl-4">
        <div class="card">
            <div class="card-body">
                <div>
                    <h1 class="h3">Enter code from sms message (valid for 5 min)</h1>
                    <form method="POST" action="/code">
                        <div class="mb-3">
                            <label for="code">Code:</label>
                            <input type="text" class="form-control" id="code" name="code" placeholder="000000">
                        </div>
                        <button type="submit" class="btn btn-outline-success" name="action" value="enter">Enter</button>
                        <button type="submit" class="btn btn-outline-primary" name="action" value="resend">Resend Code</button>
                    </form>

                    <c:if test="${not empty status}">
                            <c:if test="${status == 1}">
                                <div class="alert alert-danger mt-2 mb-0">
                                    Wrong code.
                                </div>
                            </c:if>

                            <c:if test="${status == 2}">
                                <div class="alert alert-danger mt-2 mb-0">
                                    Code expired, resend it please.
                                </div>
                            </c:if>

                            <c:if test="${status == 3}">
                                <div class="alert alert-danger mt-2 mb-0">
                                    No action choosed.
                                </div>
                            </c:if>

                            <c:if test="${status == 4}">
                                <div class="alert alert-danger mt-2 mb-0">
                                    Your code is still valid.
                                </div>
                            </c:if>

                            <c:if test="${status == 5}">
                                <div class="alert alert-success mt-2 mb-0">
                                    Code was sended.
                                </div>
                            </c:if>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</div>