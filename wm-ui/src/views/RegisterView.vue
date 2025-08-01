<script>
    import { useVuelidate } from '@vuelidate/core'
    import { helpers, maxLength, minLength, required } from '@vuelidate/validators'
    import api from '../services/api.js'

    const passwordRegex = helpers.regex(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[;:*\-!]).{8,}$/)
    const emailRegex = helpers.regex(/^(?=.{1,64}@)\w+([.-]?\w+)*@(?=.{4,252}$)\w+([.-]?\w+)*(\.\w{2,4})+$/)
    // check the email in the server
    // const isEmailTaken = (value) => fetch(`/api/unique/${value}`).then(r => r.json()) 

    export default {
        setup() {
            return {
                v$: useVuelidate()
            }
        },
        data() {
            return {
                inputs: {
                    email: "",
                    password: ""
                }
            }
        },
        validations() {
            return {
                inputs: {
                    email: { 
                        required,
                        maxLength: maxLength(255),
                        emailRegex
                    },
                    password: { 
                        required,
                        minLength: minLength(8),
                        passwordRegex 
                    }
                }
            }
        },
        methods: {
            async submit() {
                const isFormCorrect = await this.v$.$validate();
                if(!isFormCorrect) return;

                api.post('/user', {
                    email: this.inputs.email,
                    password: this.inputs.password,
                    username: this.inputs.email.split('@')[0]
                }).then(function (response) {
                    console.log("then " + response);
                }).catch(function (error) {
                    if (error.response) {
                        // The request was made and the server responded with a status code
                        // that falls out of the range of 2xx
                        console.log(error.response.data);
                        console.log(error.response.status);
                        console.log(error.response.headers);
                    } else if (error.request) {
                        // The request was made but no response was received
                        // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                        // http.ClientRequest in node.js
                        console.log("error " + error.request);
                    } else {
                        // Something happened in setting up the request that triggered an Error
                        console.log('Error', error.message);
                    }
                    console.log("last " + error.config);
                });
            },

        }
    }
</script>

<template>
    <div className="container min-vw-80 content">
        <h1>{{$t('auth.registerTitle')}}</h1>
        <form @submit.prevent="submit" novalidate>
            <div className="mb-3">
                <h2 htmlFor="email" className="form-label">{{$t('auth.emailLabel')}}</h2>
                <input v-model="inputs.email" type="email" name="email" className="form-control" id="email"/>
                <div class="text-danger" v-if="v$.inputs.email.$error">{{ $t('auth.emailError') }}</div>
            </div>
            <div className="mb-3">
                <h2 htmlFor="password" className="form-label">{{$t('auth.passwordLabel')}}</h2>
                <input v-model="inputs.password" type="password" name="password" className="form-control" id="password"/>
                <div class="text-danger" v-if="v$.inputs.password.$error">{{ $t('auth.passwordError') }}</div>
            </div>
            <button type="submit" className="btn btn-primary">{{$t('auth.submitButton')}}</button>
        </form>
    </div>
</template>