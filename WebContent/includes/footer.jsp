<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer class="footer bg-dark text-white text-center py-3 mt-5 fade-in">
    <div class="container">
        <p class="mb-1">&copy; 2025 <strong>Village Issue Reporting System</strong>. All rights reserved.</p>
        <small class="text-secondary">Designed & Developed by the Village Tech Team</small>
    </div>
</footer>

<!-- Bootstrap Bundle (JS + Popper) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
    /* Ensure footer stays at bottom if content is short */
    html, body { height: 100%; margin: 0; display: flex; flex-direction: column; }
    .footer { margin-top: auto; }

    /* Optional fade-in */
    .fade-in { opacity: 0; animation: fadeIn 0.8s forwards; }
    @keyframes fadeIn { to { opacity: 1; } }
</style>
</body>
</html>
