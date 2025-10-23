# 1️⃣ Base image
FROM node:20-alpine

# 2️⃣ Set working directory
WORKDIR /usr/src/app

# 3️⃣ Copy package files & install deps
COPY package*.json ./
RUN npm install --include=dev

# 4️⃣ Copy the rest of the code
COPY . .

# 5️⃣ Expose app port
EXPOSE 5001

# 6️⃣ Run the app
CMD ["npm", "run", "start:dev"]
