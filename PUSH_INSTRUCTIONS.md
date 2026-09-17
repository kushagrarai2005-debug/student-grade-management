# How to Push This Project to GitHub

Your local repo is already initialized and the initial commit is ready.
From this sandbox I can't reach GitHub, so you'll run the push yourself.

---

## Option A — GitHub CLI (easiest)

If you have the [GitHub CLI](https://cli.github.com/) installed:

```bash
# 1. Authenticate
gh auth login

# 2. Create the repo and push in one shot
gh repo create student-grade-management \
    --public \
    --source=. \
    --remote=origin \
    --push \
    --description "Student Grade Management System — Java console app for VIT Build Your Own Project"
```

That's it — CLI handles remote, push, and description.

---

## Option B — Manual via HTTPS + Personal Access Token

GitHub no longer accepts account passwords for HTTPS pushes, so use a **PAT (Personal Access Token)**.

### 1. Create the empty repo on GitHub
- Go to https://github.com/new
- Repository name: **`student-grade-management`**
- Description: *Student Grade Management System — Java console app*
- Choose **Public** (or Private, your call)
- **DO NOT** check "Add README", "Add .gitignore", or "Choose a license" — we already have those
- Click **Create repository**

### 2. Create a PAT (if you don't have one)
- Go to https://github.com/settings/tokens
- Click **Generate new token** → **Classic**
- Expiration: 90 days (or whatever you prefer)
- Scopes: at minimum check **`repo`**
- Click **Generate token** and **copy it immediately** (you won't see it again)

### 3. Push from your machine
Download/clone this project folder to your local machine, `cd` into it, then:

```bash
git remote set-url origin https://github.com/kushagrarai2005-debug/student-grade-management.git
git push -u origin main
```

When prompted:
- **Username:** `kushagrarai2005-debug`
- **Password:** paste your **Personal Access Token** (not your account password)

---

## Option C — SSH (most convenient long-term)

### 1. Generate an SSH key (if you don't have one)
```bash
ssh-keygen -t ed25519 -C "kushagra.rai@vitstudent.ac.in"
# press Enter for default path, set a passphrase if you want
```

### 2. Add the public key to GitHub
- Copy the contents of `~/.ssh/id_ed25519.pub`
- Go to https://github.com/settings/keys → **New SSH key**
- Title: e.g. *"My Laptop"*
- Paste the key → **Add SSH key**

### 3. Switch the remote to SSH and push
```bash
git remote set-url origin git@github.com:kushagrarai2005-debug/student-grade-management.git
git push -u origin main
```

---

## Verify It Worked

After pushing, refresh https://github.com/kushagrarai2005-debug/student-grade-management — you should see:

- ✅ 20 files
- ✅ `README.md` rendered on the homepage
- ✅ `src/`, `data/`, `test/`, `docs/` folders

## Repo Description (suggested)

> A console-based Java application for managing students, courses, and grades. Built for the VIT Build Your Own Project evaluation. Includes CSV persistence, weighted CGPA calculation, automatic letter-grade conversion, validation tests, and complete UML + report documentation.

## Topics (tags to add in the GitHub UI)

`java` `oop` `console-application` `student-management` `gradebook` `csv` `vit`
