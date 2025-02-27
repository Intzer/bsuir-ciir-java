<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-lg-6 col-xl-4 offset-lg-3 offset-xl-4">
        <div class="card">
            <div class="card-body">
                <div>
                    <h1 class="h3">Sign in</h1>
                    <form method="POST" action="/auth">
                        <div class="mb-3">
                            <label for="phoneNumber">Phone number:</label>
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="+37529.......">
                        </div>
                        <button type="submit" class="btn btn-outline-success">Sign In</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>