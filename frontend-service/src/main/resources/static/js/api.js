/* ===== API UTILITY ===== */

const API_BASE = 'http://localhost:9090';

function getToken() { return sessionStorage.getItem('token'); }
function getUser() { return JSON.parse(sessionStorage.getItem('user') || 'null'); }
function isLoggedIn() { return !!getToken(); }
function isAdmin() { const u = getUser(); return u && u.role === 'ROLE_ADMIN'; }
function isUser() { const u = getUser(); return u && u.role === 'ROLE_USER'; }

function logout() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('user');
    window.location.href = '/index.html';
}

function requireAuth(role) {
    if (!isLoggedIn()) { window.location.href = '/user-login.html'; return false; }
    if (role === 'admin' && !isAdmin()) { window.location.href = '/user-dashboard.html'; return false; }
    if (role === 'user' && !isUser()) { window.location.href = '/admin-dashboard.html'; return false; }
    return true;
}

async function apiFetch(url, options = {}) {
    const token = getToken();
    const headers = { 'Content-Type': 'application/json', ...(options.headers || {}) };
    if (token) headers['Authorization'] = `Bearer ${token}`;

    try {
        const response = await fetch(API_BASE + url, { ...options, headers });

        if (response.status === 401) { logout(); return null; }

        const text = await response.text();
        if (!text) return { ok: response.ok, status: response.status };

        let data;
        try { data = JSON.parse(text); } catch { data = text; }

        return { ok: response.ok, status: response.status, data };
    } catch (err) {
        console.error('API Error:', err);
        return { ok: false, status: 0, data: { message: 'Cannot connect to server. Make sure all services are running.' } };
    }
}

async function apiGet(url) { return apiFetch(url, { method: 'GET' }); }
async function apiPost(url, body) { return apiFetch(url, { method: 'POST', body: JSON.stringify(body) }); }
async function apiPut(url, body) { return apiFetch(url, { method: 'PUT', body: JSON.stringify(body) }); }
async function apiPatch(url, body) { return apiFetch(url, { method: 'PATCH', body: body ? JSON.stringify(body) : undefined }); }
async function apiDelete(url) { return apiFetch(url, { method: 'DELETE' }); }

function showAlert(id, msg, type = 'danger') {
    const el = document.getElementById(id);
    if (!el) return;
    el.className = `alert alert-${type} show`;
    el.textContent = msg;
    setTimeout(() => el.classList.remove('show'), 4000);
}

function formatDate(dateStr) {
    if (!dateStr) return 'N/A';
    return new Date(dateStr).toLocaleDateString('en-IN', { year: 'numeric', month: 'short', day: 'numeric' });
}

function timeAgo(dateStr) {
    if (!dateStr) return '';
    const diff = Date.now() - new Date(dateStr).getTime();
    const mins = Math.floor(diff / 60000);
    if (mins < 1) return 'just now';
    if (mins < 60) return `${mins}m ago`;
    const hrs = Math.floor(mins / 60);
    if (hrs < 24) return `${hrs}h ago`;
    return `${Math.floor(hrs / 24)}d ago`;
}

function setUserNav() {
    const user = getUser();
    if (!user) return;
    const nameEl = document.getElementById('nav-user-name');
    const avatarEl = document.getElementById('nav-user-avatar');
    if (nameEl) nameEl.textContent = user.name || user.email;
    if (avatarEl) avatarEl.textContent = (user.name || user.email || 'U')[0].toUpperCase();
}

async function loadNotificationBadge(userId) {
    if (!userId) return;
    const res = await apiGet(`/api/notifications/user/${userId}/unread-count`);
    if (res && res.ok) {
        const count = res.data.unreadCount;
        const badge = document.getElementById('notif-badge');
        if (badge) {
            badge.textContent = count > 9 ? '9+' : count;
            badge.className = count > 0 ? 'badge show' : 'badge';
        }
    }
}

async function loadNotifications(userId) {
    const res = await apiGet(`/api/notifications/user/${userId}`);
    const list = document.getElementById('notif-list');
    if (!list) return;

    if (!res || !res.ok || !res.data.length) {
        list.innerHTML = '<div class="notif-empty">🔔 No notifications yet</div>';
        return;
    }

    list.innerHTML = res.data.slice(0, 10).map(n => `
        <div class="notif-item ${!n.read ? 'unread' : ''}" onclick="markRead(${n.id}, ${userId})">
            <div class="notif-icon">🎵</div>
            <div class="notif-text">
                <div class="notif-msg">${n.message}</div>
                <div class="notif-time">${timeAgo(n.createdAt)}</div>
            </div>
        </div>
    `).join('');
}

async function markRead(notifId, userId) {
    await apiPatch(`/api/notifications/${notifId}/read`);
    loadNotifications(userId);
    loadNotificationBadge(userId);
}

function toggleNotifDropdown(userId) {
    const dd = document.getElementById('notif-dropdown');
    if (!dd) return;
    dd.classList.toggle('open');
    if (dd.classList.contains('open')) loadNotifications(userId);
}

document.addEventListener('click', (e) => {
    const dd = document.getElementById('notif-dropdown');
    const btn = document.getElementById('notif-btn');
    if (dd && btn && !dd.contains(e.target) && !btn.contains(e.target)) {
        dd.classList.remove('open');
    }
});
