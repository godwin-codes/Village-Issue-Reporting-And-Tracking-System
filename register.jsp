<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="includes/header.jsp" />

<div class="container mt-5">
    <div class="col-md-6 offset-md-3">
        <div class="card shadow p-4">
            <h3 class="text-center mb-4">User Registration</h3>
            <form action="RegisterServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">Full Name</label>
                    <input type="text" class="form-control" name="name" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Register</button>
                <p class="mt-3 text-center">Already have an account? 
                    <a href="login.jsp">Login here</a>
                </p>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/includes/footer.jsp" />
