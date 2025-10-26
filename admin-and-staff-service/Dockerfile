# ----------- 1. Base image -----------
FROM node:20-alpine AS builder

# ----------- 2. Set working directory -----------
WORKDIR /app

# ----------- 3. Copy file dependency -----------
COPY package*.json ./
# ----------- 4. Install dependency -----------

RUN npm install

# ----------- 5. Copy source code ----------- 
COPY . .

# ----------- 6. Build source code (nếu dùng TypeScript) -----------
RUN npm run build

# ----------- 7. Stage 2: Create lightweight image for runtime -----------
FROM node:20-alpine AS runner

# ----------- 8. Working directory in runtime image -----------
WORKDIR /app

# ----------- 9. Copy built files + package.json -----------
COPY --from=builder /app/dist ./dist
COPY --from=builder /app/package*.json ./

# ----------- 10. Install only production dependencies -----------
RUN npm install --omit=dev

# ----------- 11. Expose port (port app chạy) -----------
EXPOSE 3000

# ----------- 12. Command khi container khởi động -----------
CMD ["node", "dist/main.js"]
