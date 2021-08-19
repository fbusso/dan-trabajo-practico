# pull official base image
FROM node:14.15.3-alpine

# set working directory
WORKDIR /src/frontend/

ENV PATH ./frontend/node_modules/.bin:$PATH

# install app dependencies
COPY ./frontend/package.json .
COPY ./frontend/package-lock.json .
RUN npm install --silent
RUN npm install react-scripts@4.0.3 -g --silent
EXPOSE 4200

# add app
COPY . .

# start app
CMD ["npm", "start"]
